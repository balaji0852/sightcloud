import { Component, React } from "react";
import "./EditBox.css";
import { Button, FormGroup, Label, Input, Col, Form, Row } from 'reactstrap';
import { axiosGlobal } from '../interaction/one_instance';
import { BsChevronLeft, BsChevronRight, BsXLg } from "react-icons/bs";
import { colors } from "../authentication/server-state";
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import {classMaster} from "../authentication/server-state";
import ToggleSwitch from "../webrender/widgets/ToggleSwitch.js";
//bal.js, text-align : justify <- aligns text to normal
class EditBox extends Component {
    constructor(props) {
        super(props);
        this.callingServer = false;
        this.state = {
            editBoxState: true,
            comment: '',
            dataInstance: {
                "classMaster": {
                    "itemMasterID": props.state['classMaster']['itemMasterID'],
                },
                "dataInstances": "",
                "instanceTime": 0,
                "instancesStatus": 2,
                "userStore":{
                    "userStoreID": props.state['userStoreID']
                }
            },
            hourlyDataInstanceFromChild:props.state['hourlyDataInstanceFromChild'],
            index: 0
        }
    }


    //7/11/2022 - balaji: adding this function from the pg.js to global graphDialog.js
    //                     - adding type - 1 dispatch to store<-type-1 : pg-gd
    //pg.js - balaji : adding a function for cleanup/maniplt of store, with GD data
    stateCleanUpGraphDialog(stateLocal){
        this.props.dispatch({ 
            type: 'changeGraphDialog', 
            payload: stateLocal});
    }


    onClose(event) {

        let stateLocal = this.props.state;
        stateLocal.openDialog = false;
        stateLocal.hourlyDataInstanceFromChild = []
        stateLocal.type = 0;
        stateLocal.pgReRender = Math.random();
        //11/11/2022, balaji-prob, if we chnage classMaster. csp re render-prop change
        // stateLocal.classMaster = classMaster
        this.stateCleanUpGraphDialog(stateLocal);
        //this.props.onClick(event);
    }

    UNSAFE_componentWillUpdate(nextProps){
        //2/11/22, balaji: had issue with accessing empty hourly list, need 
        //              to cleanup, old state. so we added this cwu
        //              
        // if(nextProps!=this.props){
        //     this.setState({
        //         index:0,
        //         hourlyDataInstanceFromChild:nextProps.state.hourlyDataInstanceFromChild
        //     });
        // }

    }

   componentDidUpdate(nextProps){
    console.log(nextProps,this.props);

   }

   
    onComment(event) {
        console.log('1')
        let dataInstance = this.state.dataInstance;
        dataInstance['dataInstances'] = event.target.value;
        dataInstance['instanceTime'] = Date.now();
        this.setState({
            comment: event.target.value,
            dataInstance: dataInstance
        })
    }

    updateCommentStatus(event) {
        let payload = this.props.state['hourlyDataInstanceFromChild'].at(this.state.index);
        payload['instancesStatus'] = event.target.value;
        payload['instanceTime'] = payload['instancesStatus']==3?Date.now():payload['instanceTime'] ;
        const dataInstanceJSON = JSON.stringify(payload);
        axiosGlobal.put('api/dataInstanceMaster/update', dataInstanceJSON).then((response) => {

            if (response["data"] === 'success') {
                let dataInstance = this.state.dataInstance;
                dataInstance['instancesStatus'] = payload['instancesStatus'];
                this.setState({
                    dataInstance:dataInstance
                });
            }
        }).catch((response) => {


        });
    }   

    postComment() {
        if (this.state.comment.length != 0 && !this.callingServer) {
            this.callingServer = true;
            const dataInstanceJSON = JSON.stringify(this.state.dataInstance);
            axiosGlobal.post('api/dataInstanceMaster', dataInstanceJSON).then((response) => {

                if (response["data"] === 'success') {
                    this.setState({
                        comment: ""
                    });
                    this.callingServer = false;
                    //SIG-53, we are sending params for this, to avoid server call
                    //          on closing EditBox,used by csp...
                    //this.onClose(false);

                    //10/11/2022, balaji -prob, adding this new func, removing onclose
                    //              
                    let stateLocal = this.props.state;
                    stateLocal.openDialog = false;
                    stateLocal.hourlyDataInstanceFromChild = []
                    stateLocal.type = 0;
                    stateLocal.pgReRender = Math.random();
                    //11/11/2022, balaji-prob, if we chnage classMaster. csp re render-prop change
                    // stateLocal.classMaster = classMaster
                    this.stateCleanUpGraphDialog(stateLocal);
                }
            }).catch((response) => {


            });
        }
    }

    componentWillUnmount(){
        console.log('cwu');
    }

    render() {
        console.log('render',this.state);
        return <div className="EditBox">{this.props.state['type'] === 1  ?
            <div>
                <h1>{this.props.state['classMaster']['itemName']}</h1>
                <div>{this.props.state['classMaster']['description']}</div>
                <div className="text-box">
                    <FormGroup>
                        <Input placeholder='comment...' type="textarea" name="description" id="exampleText"
                            rows="13" value={this.state.comment} onChange={this.onComment.bind(this)} />
                    </FormGroup>
                </div>
                <div className="action-box">
                    <Button color="primary" onClick={this.postComment.bind(this)}>add comment</Button>
                    <Button color="primary" onClick={this.onClose.bind(this, true)} >cancel</Button>
                </div>
            </div>
            : <div className="gd">
                <div className="action-box">
                    <BsXLg className="icon" size={35} onClick={this.onClose.bind(this, true)} />
                </div>
                <div className="graph-dialog">

                    <div className="move-right">
                        <BsChevronLeft  className="icon"  size={50} onClick={() => {
                            if (this.state.index > 0) {
                                this.setState({ index: this.state.index - 1 })
                            }
                        }} />
                    </div>
                    <div className="main">
                         <h1 className="itemName">{this.state['hourlyDataInstanceFromChild'].at(this.state.index)['classMaster']['itemName']}</h1> 
                        <h4 className="description-gd">
                            {this.state['hourlyDataInstanceFromChild'].at(this.state.index)['classMaster']['description'].length != 0 && "Description : "} 
                            {this.state['hourlyDataInstanceFromChild'].at(this.state.index)['classMaster']['description']}
                            </h4>
                        <div className="main-2">
                            <div className="main-datainstances">
                                <div className="dataInstances-gd"
                                    style={{ backgroundColor: colors.at(this.state['hourlyDataInstanceFromChild'].at(this.state.index)['classMaster']['itemClassColorID']) }}>
                                    {this.state['hourlyDataInstanceFromChild'].at(this.state.index)['dataInstances'].length != 0 && "Comments : "}
                                    {this.state['hourlyDataInstanceFromChild'].at(this.state.index)['dataInstances']}</div>
                            </div>
                            <div className="action-datainstances main-2">
                                <div>status</div>
                                <Input type="select" name="select" id="exampleSelect"
                                    onChange={this.updateCommentStatus.bind(
                                        this)}
                                    value={this.state['hourlyDataInstanceFromChild'].at(this.state.index)['instancesStatus']}>
                                    <option value={3}>done</option>
                                    <option value={1}>working</option>
                                    <option value={2}>to-do</option>
                                </Input>
                            </div>
                        </div>
                    </div>
                    <div className="move-left">
                        <BsChevronRight  className="icon" size={50} onClick={() => {
                            if (this.state.index < this.props.state['hourlyDataInstanceFromChild'].length - 1) {
                                this.setState({ index: this.state.index + 1 })
                            }
                        }} />
                    </div>
                </div>
            </div>
        }
        </div>
    }
}


EditBox.propTypes = {
    state:PropTypes.object.isRequired
}

const mapStateToProps = (state) => {
    return ({
        state:state
    });
  };

export default connect(mapStateToProps)(EditBox);