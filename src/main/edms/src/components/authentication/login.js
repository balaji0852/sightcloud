import React, { Component } from 'react';
import './login.css';
import loadingDescription from './man_working_sofa.jpg';
//import ClassItemComponent from '../Planner_graph/classItemcomponent';
import { Button } from 'reactstrap';
import { Link } from 'react-router-dom';
import { axiosGlobal } from '../interaction/one_instance';
import { connect } from 'react-redux'
import PropTypes from 'prop-types';

class Login extends Component {



    onLogin(){
        //Balaji 12/12/2023:dummy userStore until auth fit
        let userStore = {
            linkedEmail: "balajikumar189@gmail.com",
            userName: "Balaji rajkumar",
            linkedPhone: "empty",
            photoURL: "https://lh3.googleusercontent.com/a-/AOh14Gi4kVhNkDmAtqvKOvHlxskLSaQKiLsTxQ9VXkBI4w=s96-c",
            themeID: -1,
            dateViewPreference: -1,
            timeViewPreference: -1
        }

        let userStoreJSON = JSON.stringify(userStore);
        axiosGlobal.put('api/userStore/update', userStoreJSON).then((response) => {

          if (response["data"]["userStoreID"] !=0 ) {
            let userStore = response["data"];
            let state = this.props.state;
            state["userStore"] = userStore;
            this.props.dispatch({ 
                type: 'updateUser', 
                payload: state});
            window.location.href = '#/projects';

          }
        }).catch((response) => {


        });
    }

    render() {
        return (
            <div className='login'>
                <div>
                    <img className='loadingDescriptionImage'
                        src={loadingDescription} />
                    <div >login, if you're a hipster...</div>
                    {/* <Link to='/projects'> */}
                        <Button onClick={()=>this.onLogin()}>login</Button>
                    {/* </Link> */}
                    <br></br>
                    <Link to='/pm?themeid=1&projectStoreID=3363&userStoreID=2519'>
                       webrender
                    </Link>
                </div>

                {/* <ClassItemComponent title="sight testing and fixes" description="Sight testing phase updates and fix will be updated here, please update for found bugs and communicate for it here..." lastComment='found bug on clicking home page, cant see the breadcrumb for the first time of boot' />
        <ClassItemComponent title="sight testing and fixes" description="Sight testing phase updates and fix will be updated here, please update for found bugs and communicate for it here..." lastComment='found bug on clicking home page, cant see the breadcrumb for the first time of boot' />
        <ClassItemComponent title="sight testing and fixes" description="Sight testing phase updates and fix will be updated here, please update for found bugs and communicate for it here..." lastComment='found bug on clicking home page, cant see the breadcrumb for the first time of boot' />
        <ClassItemComponent title="sight testing and fixes" description="Sight testing phase updates and fix will be updated here, please update for found bugs and communicate for it here..." lastComment='found bug on clicking home page, cant see the breadcrumb for the first time of boot' />
        <ClassItemComponent title="sight testing and fixes" description="Sight testing phase updates and fix will be updated here, please update for found bugs and communicate for it here..." lastComment='found bug on clicking home page, cant see the breadcrumb for the first time of boot' /> */}

            </div>
        );
    }
}

Login.propTypes = {
    state:PropTypes.object.isRequired
}

const mapStateToProps = (state) => {
    return ({
        state:state
    });
  };

export default connect(mapStateToProps)(Login);
