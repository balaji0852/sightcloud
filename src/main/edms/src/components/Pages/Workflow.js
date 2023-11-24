import { React, Component } from 'react';
import { Button, FormGroup, Label, Input, Col, Form, Row } from 'reactstrap';
import './Workflow.css';
import ColorStore from '../Planner_graph/colorStore';
import { projectStoreID ,classMaster as classMasterEmpty} from '../../components/authentication/server-state'
import { axiosGlobal } from '../interaction/one_instance';
import axios from 'axios';
import { data } from 'jquery';
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import ReduxWrapper from '../authentication/ReduxWrapper';
import { colors } from "../authentication/server-state";

class add extends Component {




  constructor(props) {
    super(props);
    this.onColorChange = this.onColorChange.bind(this);
    this.onInputchange = this.onInputchange.bind(this);
    //sig 30: added for topology
    this.callingServer = false;

    this.state = {
      selectedColor: props.state['setForEdit'] ? colors.at(props.state.classMaster['itemClassColorID']) : 'red',
      setForEdit:props.state['setForEdit'] ,
      class:
      {
        itemName: props.state['setForEdit'] ? props.state.classMaster['itemName'] : '',
        categoryID: props.state['setForEdit'] ? props.state.classMaster['categoryID'] : '',
        subCategoryID: props.state['setForEdit'] ? props.state.classMaster['subCategoryID'] : '',
        itemClassColorID: props.state['setForEdit'] ? props.state.classMaster['itemClassColorID'] : '',
        itemPriority: props.state['setForEdit'] ? props.state.classMaster['itemPriority'] : '',
        isItemCommentable: props.state['setForEdit'] ? props.state.classMaster['isItemCommentable'] : '',
        description: props.state['setForEdit'] ? props.state.classMaster['description'] : '',
        projectStore: {
          projectStoreID: props.state['projectStoreID']
        },
        createdDate: 0,
        userStore: {
          userStoreID :  props.state['setForEdit'] ?  props.state['userStoreID']:props.state['userStoreID']
        }
      }

    };
  }

  onColorChange(value) {
    var classMaster = this.state.class;
    let itemClassColorID = 0;
    classMaster['itemClassColorID'] = colors.findIndex((index, i) => {
      if (index === value) {
        itemClassColorID = i;
      }
    });

    classMaster['itemClassColorID'] = itemClassColorID;
    this.setState({
      selectedColor: value,
      class: classMaster
    });
  }

  updateDataToDatabase() {
    if (this.state.class.itemName.length != 0 && this.state.class.itemName.length <= 30 && !this.callingServer) {
      var classMaster = this.state.class;
      this.callingServer = true;
      console.log(this.callingServer);
      classMaster['createdDate'] = Date.now();
      var classMasterJSON = JSON.stringify(classMaster);
      
      if (this.state.setForEdit) {
        classMaster['itemMasterID'] = this.props.state['classMaster']['itemMasterID'];
        classMasterJSON = JSON.stringify(classMaster);
        axiosGlobal.put('api/classMaster', classMasterJSON).then((response) => {

          if (response["data"] === 'success') {
            classMaster["itemName"] = "";
            classMaster["description"] = "";
            this.setState({
              class: classMaster,
              selectedColor: 'red',
              setForEdit:false
            });
            this.callingServer = false;
            window.location.href = '#/dashboard/class';

          }
        }).catch((response) => {


        });
      } else {
        axiosGlobal.post('api/classMaster', classMasterJSON).then((response) => {

          if (response["data"] === 'success') {
            classMaster["itemName"] = "";
            classMaster["description"] = "";
            this.setState({
              class: classMaster,
              selectedColor: 'red'
            });
            this.callingServer = false;
            window.location.href = '#/dashboard/class';
          }
        }).catch((response) => {


        });
      }
    }
  }



  onInputchange(event) {
    var classMaster = this.state.class;
    classMaster[event.target.name] = event.target.value;
    this.setState({
      class: classMaster
    });
  }


  componentWillUnmount() {
    let state = this.props.state;
    //11/11/2022 - balaji - prob, 
    //          projectstore->home->class(try edit)(check the store state)->
    //          Updating an edit is corrupting class master, in the store. Cleanup required.

    state['classMaster'] = classMasterEmpty;
    state['setForEdit'] = false;
    this.props.dispatch({ type: 'changeClass', payload: state });
  }



  render() {
    return <ReduxWrapper>
      <div className='classForm'>
        <h1>Add Class</h1>
        <FormGroup>
          <Input bsSize="lg" name="itemName" placeholder='classname'
            onChange={this.onInputchange}
            value={this.state.class.itemName} />
        </FormGroup>
        <FormGroup>
          <label>Select color</label>
          <ColorStore onSelected={this.onColorChange} selectedColor={this.state.selectedColor} />
        </FormGroup>
        <FormGroup row>
          <Label for="exampleSelect" lg={1} sm={1} xs={1} >Category</Label>
          <Col lg={5} sm={5} xs={8}>
            <Input type="select" name="select" id="exampleSelect">
              {/* <option>1</option>
          <option>2</option>
          <option>3</option>
          <option>4</option>
          <option>5</option> */}
            </Input>
          </Col>
        </FormGroup>
        <FormGroup row>
          <Label for="exampleSelect" lg={1} sm={1} xs={1}>Sub-Category</Label>
          <Col lg={5} sm={5} xs={8}>
            <Input type="select" name="select" id="exampleSelect" >
              {/* <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option> */}
            </Input>
          </Col>
        </FormGroup>
        <FormGroup>
          <Label for="exampleText">Description</Label>
          <Input placeholder='Class Description' type="textarea" name="description" id="exampleText"
            onChange={this.onInputchange} value={this.state.class.description} rows="6" />
        </FormGroup>
        <FormGroup>
          <Button color="primary" onClick={this.updateDataToDatabase.bind(this)}>{this.state.setForEdit ?'update':'Add class' }</Button>
        </FormGroup>
      </div >;
    </ReduxWrapper>;
  }
}

add.propTypes = {
  state: PropTypes.object.isRequired
}

const mapStateToProps = (state) => {
  return ({
    state: state
  });
};


export default connect(mapStateToProps)(add);