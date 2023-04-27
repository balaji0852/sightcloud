import React, { Component } from 'react';
import './login.css';
import loadingDescription from './man_working_sofa.jpg';
//import ClassItemComponent from '../Planner_graph/classItemcomponent';
import { Button } from 'reactstrap';
import { Link } from 'react-router-dom';
class Login extends Component {

    render() {
        return (
            <div className='login'>
                <div>
                    <img className='loadingDescriptionImage'
                        src={loadingDescription} />
                    <div >login, if you're a hipster...</div>
                    <Link to='/projects'>
                        <Button>login</Button>
                    </Link>
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

export default Login;
