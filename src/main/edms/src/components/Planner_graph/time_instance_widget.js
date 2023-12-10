import { Component, React } from "react";
import './time_instance_widget.css'
import { axiosGlobal as axios } from '../interaction/one_instance';
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import { colors } from "../authentication/server-state";


//bal.js update for components is not only for its state, even for its props...
//bal.js even componentWillReceiveprops have nextprop
class timeInstanceWidget extends Component {

    constructor(props) {

        super(props);
        this.state = {
            query: 'empty',
            dataInstances: [],
            dataInstance: [],
            dateTimeEpoch: 0,
            zeroDateTimeEpoch: 0
        }


    }


    componentWillUnmount() {

    }


    componentDidMount() {
        console.log(`${this.props.id}  ---- cdm`)
        this.getTodayInstance();
    }



    


    getTodayInstance() {
        let dateTimeEpoch = new Date(new Date(this.props.dateTimeEpoch).toString().substring(0, 15)).getTime();
        let zeroDateTimeEpoch = new Date(new Date(this.props.zeroDateTimeEpoch).toString().substring(0, 15)).getTime();
        let itemMasterID = this.props.itemMasterID;
        let statusType = this.props.instanceStatus;
        let viewType = this.props.viewType;
        let projectStoreID = this.props.projectStoreID;
        let graphType = this.props.graphType;
        var query = `api/dataInstanceMaster/`;
        if (graphType === 1) {
            if (statusType == 0) {
                query = `api/dataInstanceMaster/query1?dateTimeEpoch=${dateTimeEpoch}&zeroDateTimeEpoch=${zeroDateTimeEpoch}&itemMasterID=${itemMasterID}`;
            } else {
                query = `api/dataInstanceMaster/status/query1?dateTimeEpoch=${dateTimeEpoch}&zeroDateTimeEpoch=${zeroDateTimeEpoch}&itemMasterID=${itemMasterID}&instanceStatus=${statusType}`;
            }
        } else {
            if (statusType == 0) {
                query = `api/dataInstanceMaster/query2?dateTimeEpoch=${dateTimeEpoch}&zeroDateTimeEpoch=${zeroDateTimeEpoch}&projectStoreID=${projectStoreID}`;
            } else {
                query = `api/dataInstanceMaster/status/query2?dateTimeEpoch=${dateTimeEpoch}&zeroDateTimeEpoch=${zeroDateTimeEpoch}&projectStoreID=${projectStoreID}&instanceStatus=${statusType}`;

            }
        }
        this.getTodayInstanceFromServer(query);

    }
    //9/9 balaji:method not aligned with mbc, with its naming and existence
    getTodayInstanceFromServer(query) {
        axios.get(query).then((response) => {
            try {
                console.log('query')
                if (response['data'] != undefined
                    && response['data'].length != 0) {
                    this.processTodayData(response['data'])
                }
                else {
                    this.setState({
                        dataInstance: []
                    });
                }
            } catch (Error) {
            }
        });
    }



    processTodayData(todayInstance) {
        // if (todayInstance.length != 0) {
        let data = [];
        for (let i = 0; i < 24; i++) {
            data.push([]);
        }
        todayInstance.map((dataInstance) => {
            let date = new Date(dataInstance['instanceTime']);
            data.at(date.getHours()).push(dataInstance);
        });
        this.setState({
            dataInstance: data
        })
    }

    componentDidUpdate(nextProps) {
        const { dateTimeEpoch } = this.props;
        const { instanceStatus } = this.props;
        const { viewType } = this.props;
        console.log("state change");
        if (nextProps.dateTimeEpoch != dateTimeEpoch || nextProps.instanceStatus != instanceStatus
            || nextProps.test != this.props.test  ) {
            //   || nextProps.test != this.props.test
            //balaji : 10/9/22 adding test for rerendering the tiw.js
            //balaji : sig-43 , on async response showing the old states data, previous date data, 
            //          happens during changing to lower views

            //balaji : 18/10/22-sig-53 temp fix, may be a long fix too, the below if(conti)
            if (nextProps.test == this.props.test) {
                this.setState({
                    dataInstance: []
                });
             }

            this.getTodayInstance();
        }
    }



    render() {

        return <>
            <div className={this.props.viewType === 1 ? "timeInstanceWidget vp1" :
                this.props.viewType === 2 ? "timeInstanceWidget vp2" :
                    this.props.viewType === 3 ? "timeInstanceWidget vp3" :
                        "timeInstanceWidget vp5"}>{
                    this.state.dataInstance.map((hourslyItems, index) => {
                        //todo :: balaji: added key for hourly items , need check using random key is a good practice.
                        return <div key={Math.random()}
                            //balaji : sig-55, fitting the redux for gd
                            onClick={() => {

                                if (hourslyItems.length != 0) {
                                    let stateLocal = this.props.state;
                                    stateLocal.openDialog = true;
                                    stateLocal.hourlyDataInstanceFromChild = hourslyItems;
                                    stateLocal.type = 2;
                                    stateLocal.pgReRender = 0;
                                    this.props.dispatch({
                                        type: 'changeGraphDialog',
                                        payload: stateLocal
                                    });
                                }
                            }}
                            className="dataInstances" >{hourslyItems.map((items) => {
                                return <div className="items" key={items['dataInstanceID']} style={{ backgroundColor: colors.at(items['classMaster']['itemClassColorID']) }} >
                                    {items['dataInstanceID'] + items['dataInstances']}
                                </div>
                            })}</div>
                    })
                }
            </div>
        </>;
    }
}


timeInstanceWidget.propTypes = {
    state: PropTypes.object.isRequired
}

const mapStateToProps = (state) => {
    return ({
        state: state
    });
};


export default connect(mapStateToProps)(timeInstanceWidget);