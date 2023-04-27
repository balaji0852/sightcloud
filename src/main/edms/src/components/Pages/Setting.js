import React, { Component } from 'react';
import './Setting.css';
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import ReduxWrapper from '../authentication/ReduxWrapper';

class Setting extends Component {


  constructor() {
    super();
    this.state = {
      location: ''
    }
  }

  componentDidMount() {

  }

  render() {

    return (
      <ReduxWrapper>
        <div className="setting">
          <h3>Settings</h3>
          <h5>Project id - {this.props.state['projectStoreID']}</h5>
          <div>v6.0.1</div>
        </div>
      </ReduxWrapper>
    );
  }

}

const mapStateToProps = (state) => {
  return ({
    state: state
  });
};


export default connect(mapStateToProps)(Setting);