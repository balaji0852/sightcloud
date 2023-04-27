import React, { Component } from 'react';
import './project.css';
import { BsArrowLeft } from "react-icons/bs";
import { Link, Redirect } from 'react-router-dom';
import { Button, FormGroup, Label, Input, Col, Form, Row } from 'reactstrap';


class project extends Component {

    constructor(props){
        super(props);
        // this.navigate = useHistory();
        // this.navigateToContacts = this.navigateToContacts.bind(this);
    }

    postClassMaster(){
        
    }

    navigateToContacts = () => {
        return <Redirect to='/dashboard/home'/>;
    };

    render() {
        return <div className='project'>
            <div className='header'>
                {/* <Link className="link" to='/dashboard/home'> */}
                {/* onClick={()=> window.location.href='#/dashboard/home'} */}
                <BsArrowLeft size={40}  onClick={()=> window.location.href='#/projects'}/>
                {/* </Link> */}
                <h1>Project Creation</h1>
            </div>
            <Form className='form'>
                <FormGroup row>
                    <Label for="projectName" lg={2} sm={3} xs={3}>Project Name</Label>
                    <Col lg={10} sm={10} xs={11}>
                        <Input id="projectName" placeholder="Project Name" type="text" bsSize='lg' />
                    </Col>
                </FormGroup>
                <FormGroup row>
                    <Label for="Description" lg={2} sm={3} xs={3} >Description</Label>
                    <Col lg={10} sm={10} xs={11}>
                        <Input id="descriptionTextInput" placeholder="Project Description" type="textarea" rows="5" />
                    </Col>
                </FormGroup>
                <FormGroup row>
                    <Label for="tierType" lg={2} sm={1} xs={2}>Service Plan</Label>
                    <Col lg={4} sm={4} xs={6}>
                        <Input id="tierTypeSelection" type="select">
                            <option>free tier</option>
                        </Input>
                    </Col>
                </FormGroup>
            </Form>
            <Button color="primary">Create Project</Button>
        </div>;
    }
}

export default project;