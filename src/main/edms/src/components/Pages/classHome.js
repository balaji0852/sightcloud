import React, { Component } from 'react';
import './classHome.css';
import ClassItemComponent from '../Planner_graph/classItemcomponent';
import { axiosGlobal as axios } from '../interaction/one_instance';
import loadingDescription from './save-organisation.jpg';
import CommonDescriptor from './loaders/common_descriptor';
import ErrorBoundary from './loaders/error_boundary';
import { projectStoreID, userStoreID } from '../authentication/server-state';
import failureLoadingDescription from '../image-store/team-project-work.jpg';
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import ReduxWrapper from '../authentication/ReduxWrapper';

//bal.js need Uppercase for class naming in react

class classHome extends Component {


  constructor(props) {
    super(props);
    console.log(this.props.state)
    this.state = {
      location: '',
      loaded: false,
      classitems: [],
      status: 'contains',
      statusMessage: "We are getting all your esssential work data..."
    }
  }

  componentDidMount() {
    this.getAllClassItems();
  }


  //13/3/2023 : Balaji updating the api to new "new get, with pin, and lstcomment order"
  getAllClassItems = () => {
    axios.get(`/api/classMaster/directory/projectStore?projectStoreID=${this.props.state['projectStoreID']}
    &userStoreID=${userStoreID}`).then((response) => {

      let statusNow;
      let isLoaded = false;
      let statusMessage = this.state.statusMessage;
      if (response['data'].length === 0) {
        statusNow = 'contains';
        statusMessage = "Add you're work data and collaborate with you're teams..."
      }
      else if (response['data'].length >= 1) {
        statusNow = 'contains';
        isLoaded = !isLoaded;
        console.log(response)
      }
      this.setState({
        status: statusNow,
        loaded: isLoaded,
        classitems: response['data'],
        statusMessage: statusMessage
      });

    }).catch((response) => {
      if (response['code'] === 'ECONNABORTED') {
        this.setState({
          status: 'error',
        });
      }
    });

  }

  doQueryToServer(itemMasterID){
    this.getAllClassItems();
  }


  //13/03/2023 : balaji - changing ClassItemComponent params value, since moving from
  //                        "get item by projectStoreID" to "new get, with pin, and lstcomment order"
  //13/03/2023 : balaji - adding comments param since moving from
  //                        "get item by projectStoreID" to "new get, with pin, and lstcomment order"
  render() {

    return <ReduxWrapper>{this.state.loaded ?

      <div className="classHome">{
        this.state.classitems.map((value, index) => {
          return <ErrorBoundary   key={index}>
            <ClassItemComponent 
              // key={value['itemMasterID']} title={value['itemName']}
              // description={value['description']}
              // itemMasterID={value['itemMasterID']}
              // itemClassColorID={value['itemClassColorID']}
              // classMaster={value} 

              key={index}
              title={value['classMaster']['itemName']}
              description={value['classMaster']['description']}
              itemMasterID={value['classMaster']['itemMasterID']}
              itemClassColorID={value['classMaster']['itemClassColorID']}
              classMaster={value['classMaster']} 
              lastComment={value['dataInstances']}
              lastCommentedUser={value['userStore']}
              doQuery={(itemMasterID)=>this.doQueryToServer()}/>
          </ErrorBoundary>
        })
      }</div>
      :
      <CommonDescriptor
        status={this.state.status}
        description={this.state.statusMessage}
        loadingDescription={loadingDescription}
        failureDescription="oops, something went wrong..."
        failureLoadingDescription={failureLoadingDescription}
      />
    }</ReduxWrapper>;

  }

}

classHome.propTypes = {
  state: PropTypes.object.isRequired
}

const mapStateToProps = (state) => {
  return ({
    state: state
  });
};


export default connect(mapStateToProps)(classHome);