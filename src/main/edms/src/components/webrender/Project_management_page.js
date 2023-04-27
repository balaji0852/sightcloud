import { Switch } from "@mui/material";
import { Component, React } from "react";
import { axiosGlobal } from "../interaction/one_instance";
import "./Project_management_page.css";
import ManageUser from "./Project_management_widget/manage_user";
import ToggleSwitch from "./widgets/ToggleSwitch.js";

class ProjectManagementPage extends Component {


    constructor(props) {
        super(props)
        let params = new URLSearchParams(this.props['location']['search']);
        this.state = {
            data: {
                "projectSettingID": 0,
                "projectStore": {
                    "deactivateProject": false,
                    "servicePlanStore": {
                        "regionStore": {
                            "regionID": 0,
                            "regionName": " ",
                            "regionDescription": " ",
                            "server": " "
                        },
                        "serviceID": 0,
                        "serviceName": " ",
                        "serviceDescription": " "
                    },
                    "projectName": " ",
                    "projectDescription": " ",
                    "projectStoreID": 0
                },
                "carryForwardMyWork": false
            },

            
            themeid: params.get('themeid') ? params.get('themeid') : undefined,
            projectStoreID: params.get('projectStoreID') ? params.get('projectStoreID') : undefined,
            userStoreID: params.get('userStoreID') ? params.get('userStoreID') : undefined
        }


    }

    putProjectSetting(ProjectSetting) {

        let ProjectSettingJSON = JSON.stringify(ProjectSetting);
        axiosGlobal.put(`api/projectSetting`, ProjectSettingJSON).then((response) => {
            console.log(response);
            if (response.data == 'success') {
                this.setState({
                    data: ProjectSetting
                });
            }
        });
    }

    getProjectSetting(projectStoreID) {
        axiosGlobal.get(`api/projectSetting/${projectStoreID}`).then((response) => {
            if (response['request']['status'] == 200) {
                let responseObj = response['data'];
                this.setState({
                    data: responseObj
                })
            }
        });
    }


    componentDidMount() {
        if (this.state.projectStoreID) {
            this.getProjectSetting(this.state.projectStoreID)
        }
    }

    handleChange(event) {
        let temp = this.state.data;

        if (event.target.name == 'CFMW') {
            // temp[event.target.name] = !this.state.data.CFMW;
            temp['carryForwardMyWork'] = !this.state.data['carryForwardMyWork'];
        }
        this.putProjectSetting(temp);
    }


    render() {



        return (
            <div className={this.state.themeid==1?"PMPageDark PMPage":"PMPage "}>
                <div className="CFMW">
                    <div className="toggle">
                        <ToggleSwitch id="CFMW"
                            checked={this.state.data['carryForwardMyWork']}
                            onChange={(event) => { this.handleChange(event) }} />
                    </div>
                    <div className="CFMWTitle" >
                       Turning on CFMW (carry forward my work) will carry forward all the unfinished tasks to next day, For the entire project.
                    </div>
                </div>
                <div className={this.state.themeid==1?"line darkLine":"line liteLine"}>i</div>
                <ManageUser projectStoreID={this.state.projectStoreID} userStoreID={this.state.userStoreID} themeid={this.state.themeid} />
                <div className={this.state.themeid==1?"line darkLine":"line liteLine"}>i</div>
                <div className="projectdetails">
                    <h6 >Project details</h6>
                    <div>project id          - {this.state.data.projectStore.projectStoreID}</div>
                    <div>project name        - {this.state.data.projectStore.projectName}</div>
                    <div>Server region       - {this.state.data.projectStore.servicePlanStore.regionStore.regionName}</div>
                    <div>Service tier        - {this.state.data.projectStore.servicePlanStore.serviceName}</div>
                    <div className="Service-tier-details"><div>Service tier details -</div><div>{this.state.data.projectStore.servicePlanStore.serviceDescription.split("*").map((notes) => {
                        if (notes.length) {
                            return <div>* {notes}</div>
                        }
                    })}</div>
                    </div>
                </div>

            </div>
        );
    }
}


export default ProjectManagementPage;