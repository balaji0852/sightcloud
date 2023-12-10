import { Component, React } from "react";
import "./planner_graph.css";
import TimeInstanceWidget from "./time_instance_widget";
import { Button, FormGroup, Label, Input, Col } from 'reactstrap';
import { BsChevronLeft, BsChevronRight } from "react-icons/bs";
import { projectStoreID } from "../authentication/server-state";
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import EditBox from "./EditBox";
//Balaji - JS talk ----> without bind you can't directly pass params to a function,
//                          and should happen with the 'this' as also as params       
class plannerGraph extends Component {



    constructor(props) {
        super(props);

        this.month = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];



        this.hours = [...Array(24).keys()];

        let day = Date.now();
        this.day5 = day - 4 * 86400000;
        this.day4 = day - 3 * 86400000; 
        this.day3 = day - 2 * 86400000;
        this.day2 = day - 86400000;
        this.day1 = day;


        this.state = {
            viewPreference: props.state['viewPreference'],
            dates:  props.state['viewPreference']===1?[ this.day1]:props.state['viewPreference']===2?[ this.day2,this.day1]:props.state['viewPreference']===3?[ this.day3,this.day2,this.day1]:[ this.day5,this.day4,this.day3,this.day2,this.day1],
            projectStoreID: props.state['projectStoreID'],
            instanceStatus: 0,
            openDialog:false
        }




    }

    ChangeDateViewHandler = (viewPreferenceValue) => {
        this.dateSetter(false, true, viewPreferenceValue);
        this.setState({
            viewPreference: viewPreferenceValue
        });
        let stateLocal = this.props.state;
        stateLocal.viewPreference = viewPreferenceValue;
        this.props.dispatch({ 
            type: 'changeGraphDialog', 
            payload: stateLocal});
    }

    componentDidUpdate() {
        //this.dateSetter(false,false);
        console.log("cdu");

    }

    componentWillUnmount(){
        //1/11/2022: balaji: calling this.stateCleanUpGraphDialog();.
        //                    for pg gd cleanup
        // this.stateCleanUpGraphDialog();
        window.scrollTo({
            top:0,
             behavior: 'smooth',
        });
    }
    componentDidMount() {
        //balaji-sig-32-----contructor initialization may be lazy, but not needed to 
        //                  to ininilize state after rendering component...

        //this.initializeDate();
        //this.addDateToList(2);
        // console.log("componentDidMount " + this.state);
        console.log(document.documentElement.scrollHeight)
        window.scrollTo({
            top:(new Date().getHours()/24)*4500,
            //  behavior: 'smooth',
        });
    }

    dateSetter(isIncrement, isIntl, viewPreference) {
        if (viewPreference === 1) {
            this.day1 = isIntl ?
                this.day1
                : isIncrement
                    ? this.day1 + 86400000
                    : this.day1 - 86400000;
        } else if (viewPreference === 2) {
            this.day2 = isIntl ?
                this.day2
                : isIncrement
                    ? this.day1 + 86400000
                    : this.day2 - 2 * 86400000;
            this.day1 = this.day2 + 86400000;
        } else if (viewPreference === 3) {
            this.day3 = isIntl ?
                this.day3
                : isIncrement
                    ? this.day1 + 86400000
                    : this.day3 - 3 * 86400000;
            this.day2 = this.day3 + 86400000;
            this.day1 = this.day3 + 2 * 86400000;
        } else if (viewPreference === 5) {
            this.day5 = isIntl ?
                this.day5
                : isIncrement
                    ? this.day1 + 86400000
                    : this.day5 - 5 * 86400000;
            this.day4 = this.day5 + 86400000;
            this.day3 = this.day5 + 2 * 86400000;
            this.day2 = this.day5 + 3 * 86400000;
            this.day1 = this.day5 + 4 * 86400000;
        }
        this.addDateToList(viewPreference);
    }

    initializeDate() {
        let day = Date.now();
        this.day5 = day - 4 * 86400000;
        this.day4 = day - 3 * 86400000;
        this.day3 = day - 2 * 86400000;
        this.day2 = day - 86400000;
        this.day1 = day;
    }

    addDateToList(viewPreference) {
        let datesList = [];
        // this.setState({
        //     dates: []
        // });
        if (viewPreference === 5) {
            datesList.push(this.day5);
            datesList.push(this.day4);
            datesList.push(this.day3);
            datesList.push(this.day2);
            datesList.push(this.day1);
        } else if (viewPreference === 3) {
            datesList.push(this.day3);
            datesList.push(this.day2);
            datesList.push(this.day1);
        } else if (viewPreference === 2) {
            datesList.push(this.day2);
            datesList.push(this.day1);
        } else {
            datesList.push(this.day1);
        }
        this.setState({
            dates: datesList
        });

    }

    

    setInstanceStatus(event) {
        let _instanceStatus = event.target.value;
        this.setState({
            instanceStatus: _instanceStatus
        });
    }

    render() {

        console.log(this.props.state);

        return <div>
            <div className={this.props.graphType == 2 ? "mainToolbar" : "mainToolbarextended"}>
                <div className="toolBar" >
                    <div className="filter">
                        <div>{}</div>
                        <div className="dateViewLabel">filter</div>
                        <Input type="select" name="select" id="exampleSelect" onChange={
                            this.setInstanceStatus.bind(this)} >
                            <option value={0}>all</option>
                            <option value={3}>done</option>
                            <option value={1}>working</option>
                            <option value={2}>to-do</option>
                        </Input>
                    </div>
                    <div className="dateView">
                        <div className="dateViewLabel">view</div>
                        <Button onClick={this.ChangeDateViewHandler.bind(this, 1)}>1</Button>
                        <Button onClick={this.ChangeDateViewHandler.bind(this, 2)}>2</Button>
                        <Button onClick={this.ChangeDateViewHandler.bind(this, 3)}>3</Button>
                        <Button onClick={this.ChangeDateViewHandler.bind(this, 5)}>5</Button>
                    </div>
                    <div className="dateChange">
                        <div className="dateViewLabel">date</div>
                        <BsChevronLeft size={30} onClick={this.dateSetter.bind(this, false, false, this.state.viewPreference)} className="dateChangeButton icon" />
                        <BsChevronRight size={30} onClick={this.dateSetter.bind(this, true, false, this.state.viewPreference)} className="dateChangeButton icon" />
                    </div>
                </div >

                <div className="dateRow">
                    {
                        this.state.dates.map((item, index) => {
                            var d = new Date(item);

                            return <div key={index} className="dateRowItem">
                                {d.getDate() + " " + this.month[d.getMonth()].substring(0, 3)}
                            </div>
                        })
                    }
                </div>
            </div>
            <div className="planner-graph">
                <div className="time_column">
                    {this.hours.map((time, index) => {
                        return <div key={index * 2} className="time">{
                            time
                        }</div>
                    })}
                </div>
                {
                    this.state.viewPreference == 5 ?
                        <>

                            <TimeInstanceWidget
                                id={1}
                                graphType={this.props.graphType}
                                dateTimeEpoch={this.state.dates.at(0)}
                                zeroDateTimeEpoch={this.state.dates.at(1)}
                                itemMasterID={this.props.itemMasterID}
                                projectStoreID={this.state.projectStoreID}
                                instanceStatus={this.state.instanceStatus}
                                viewType={this.state.viewPreference}
                                test={this.props.test} />
                            <TimeInstanceWidget
                                id={2}
                                graphType={this.props.graphType}
                                dateTimeEpoch={this.state.dates.at(1)}
                                zeroDateTimeEpoch={this.state.dates.at(2)}
                                itemMasterID={this.props.itemMasterID}
                                projectStoreID={this.state.projectStoreID}
                                instanceStatus={this.state.instanceStatus}
                                viewType={this.state.viewPreference} 
                                test={this.props.test} />
                            <TimeInstanceWidget
                                id={3}
                                graphType={this.props.graphType}
                                dateTimeEpoch={this.state.dates.at(2)}
                                zeroDateTimeEpoch={this.state.dates.at(3)}
                                itemMasterID={this.props.itemMasterID}
                                projectStoreID={this.state.projectStoreID}
                                instanceStatus={this.state.instanceStatus}
                                viewType={this.state.viewPreference} 
                                test={this.props.test} />
                            <TimeInstanceWidget
                               id={4}
                                graphType={this.props.graphType}
                                dateTimeEpoch={this.state.dates.at(3)}
                                zeroDateTimeEpoch={this.state.dates.at(4)}
                                itemMasterID={this.props.itemMasterID}
                                projectStoreID={this.state.projectStoreID}
                                instanceStatus={this.state.instanceStatus}
                                viewType={this.state.viewPreference} 
                                test={this.props.test} />
                            <TimeInstanceWidget
                                id={5}
                                graphType={this.props.graphType}
                                dateTimeEpoch={this.state.dates.at(4)}
                                zeroDateTimeEpoch={this.state.dates.at(4) + 86400000}
                                itemMasterID={this.props.itemMasterID}
                                projectStoreID={this.state.projectStoreID}
                                instanceStatus={this.state.instanceStatus}
                                viewType={this.state.viewPreference} 
                                test={this.props.test} />
                        </> : this.state.viewPreference == 3 ?
                            <>



                                <TimeInstanceWidget
                                    id={6}
                                    graphType={this.props.graphType}
                                    dateTimeEpoch={this.state.dates.at(0)}
                                    zeroDateTimeEpoch={this.state.dates.at(1)}
                                    itemMasterID={this.props.itemMasterID}
                                    projectStoreID={this.state.projectStoreID}
                                    instanceStatus={this.state.instanceStatus}
                                    viewType={this.state.viewPreference} 
                                    test={this.props.test} />
                                <TimeInstanceWidget
                                    id={7}
                                    graphType={this.props.graphType}
                                    dateTimeEpoch={this.state.dates.at(1)}
                                    zeroDateTimeEpoch={this.state.dates.at(2)}
                                    itemMasterID={this.props.itemMasterID}
                                    projectStoreID={this.state.projectStoreID}
                                    instanceStatus={this.state.instanceStatus}
                                    viewType={this.state.viewPreference} 
                                    test={this.props.test} />
                                <TimeInstanceWidget
                                    id={8}
                                    graphType={this.props.graphType}
                                    dateTimeEpoch={this.state.dates.at(2)}
                                    zeroDateTimeEpoch={this.state.dates.at(2) + 86400000}
                                    itemMasterID={this.props.itemMasterID}
                                    projectStoreID={this.state.projectStoreID}
                                    instanceStatus={this.state.instanceStatus}
                                    viewType={this.state.viewPreference} 
                                    test={this.props.test} />
                            </> : this.state.viewPreference == 2 ?
                                <>


                                    <TimeInstanceWidget
                                        id={11}
                                        graphType={this.props.graphType}
                                        dateTimeEpoch={this.state.dates.at(0)}
                                        zeroDateTimeEpoch={this.state.dates.at(1)}
                                        itemMasterID={this.props.itemMasterID}
                                        projectStoreID={this.state.projectStoreID}
                                        instanceStatus={this.state.instanceStatus}
                                        viewType={this.state.viewPreference} 
                                        test={this.props.test} />
                                    <TimeInstanceWidget
                                        id={10}
                                        graphType={this.props.graphType}
                                        dateTimeEpoch={this.state.dates.at(1)}
                                        zeroDateTimeEpoch={this.state.dates.at(1) + 86400000}
                                        itemMasterID={this.props.itemMasterID}
                                        projectStoreID={this.state.projectStoreID}
                                        instanceStatus={this.state.instanceStatus}
                                        viewType={this.state.viewPreference} 
                                        test={this.props.test} />
                                </> :
                                <>
                                    <TimeInstanceWidget
                                       id={11}
                                        graphType={this.props.graphType}
                                        dateTimeEpoch={this.state.dates.at(0)}
                                        zeroDateTimeEpoch={this.state.dates.at(0) + 86400000}
                                        itemMasterID={this.props.itemMasterID}
                                        projectStoreID={this.state.projectStoreID}
                                        instanceStatus={this.state.instanceStatus}
                                        viewType={this.state.viewPreference} 
                                        test={this.props.test} />
                                </>
                }
            </div>
            {/* this.props.state['openDialog']===true && */}
            {/* { 
           this.props.state['openDialog'] &&
            <EditBox type={1}
            state={this.props.state}
            onClick={(openDialog)=>{
            //    this.stateCleanUpGraphDialog();
            }}
            />} */}
        </div >;
    }
}


plannerGraph.propTypes ={
    state:PropTypes.object.isRequired
}
  
  const mapStateToProps = (state) => {
    return ({
        state:state
    });
  };
  
  
  export default connect(mapStateToProps)(plannerGraph);