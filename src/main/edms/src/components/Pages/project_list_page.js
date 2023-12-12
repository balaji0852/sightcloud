import { Component, React } from "react";
import './project_list_page.css';
import { BsArrowLeft } from "react-icons/bs";
import { axiosGlobal as axios } from '../interaction/one_instance';
import { userStoreID } from "../authentication/server-state";
import { Link } from 'react-router-dom';
import loadingDescription from './save-organisation.jpg';
import CommonDescriptor from './loaders/common_descriptor';
import failureLoadingDescription from '../image-store/team-project-work.jpg';
import { Button } from 'reactstrap';
import { connect } from 'react-redux'
import PropTypes from 'prop-types';

class ProjectList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            loaded: false,
            projectItems: [],
            status: 'contains',
            statusMessage: "We are fetching you're mission critical business-projects...",
            themeID: this.props.state['userStore']['themeID'],
            themeCSS: {
                color: this.props.state['userStore']['themeID'] == 1 ? "White" : "Black",
                backgroundColor: this.props.state['userStore']['themeID'] == 1 ? "Black" : "White",
                Height: "100vh"
            }
        }
    }


    getProjects() {
        axios.get(`api/projectStore/projects?userStoreID=${userStoreID}`).then((response) => {

            let statusNow;
            let isLoaded = false;
            let statusMessage = this.state.statusMessage;
            if (response['data'].length === 0) {
                statusNow = 'empty';
                statusMessage = ""
            }
            else if (response['data'].length >= 1) {
                statusNow = 'contains';
                isLoaded = !isLoaded;
            }
            this.setState({
                status: statusNow,
                loaded: isLoaded,
                projectItems: response['data'],
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

    componentDidMount() {
        this.getProjects();
    }

    render() {
        return <div className="project_list_page" style={this.state.themeCSS}>
            <div className='header'>
                <BsArrowLeft size={40} onClick={() =>{
                    window.location.href =this.state.projectItems.length===0?'#/project': '#/dashboard/home'
                    if(this.state.projectItems.length>=1){
                        this.props.dispatch({ type: 'changeProject', payload: {projectStoreID:this.state.projectItems.at(0)['projectStoreID']} });
                    }
                    }} />
                <h1>Projects</h1>
            </div>
            <div className="create_project"><Button color="primary"
                onClick={()=>{
                    
                     window.location.href = '#/project';}}>Create Project</Button></div>{
                this.state.loaded ?
                    <div>
                        {this.state.projectItems.map((items, index) => {
                            return <div key={items['projectStoreID']}>
                                <div className='project_header'>
                                    <Link 
                                    onClick={()=>{
                                       this.props.dispatch({ type: 'changeProject', payload: {projectStoreID:items['projectStoreID']} });
                                    }}
                                    to='dashboard/home'>{items['projectName']}</Link>
                                    <div>{`- tier free p${items['servicePlanID']}`}</div>
                                    <div>{`- creator : admin`}</div>
                                </div>
                                <div className='project_description'>
                                    {
                                        items['projectDescription'].length != 0 ?
                                            `description : ${items['projectDescription']}` :
                                            ''
                                    }
                                </div>
                            </div>
                        })}
                    </div> : 
                <CommonDescriptor
                        status={this.state.status}
                        description={this.state.statusMessage}
                        loadingDescription={loadingDescription}
                        failureDescription="oops, something went wrong..."
                        failureLoadingDescription={failureLoadingDescription}
                    />
            }
        </div>;
    }
}

ProjectList.propTypes = {
    state: PropTypes.object.isRequired
}

const mapStateToProps = (state) => {
    return ({
        state: state
    });
};

export default connect(mapStateToProps)(ProjectList)


