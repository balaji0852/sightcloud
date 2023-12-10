import { Component, React } from "react"
import "./manage_user.css"
import { TextField } from "@mui/material";
import Button from '@mui/material/Button';
import { axiosGlobal } from "../../interaction/one_instance";
import { Link } from "react-router-dom";

class ManageUser extends Component {

    constructor(props) {
        super(props)

        this.state = {
            data: [],
            inviteeMail:"",
            actionBarOpenFor: 0
        }
    }

    getProjectMembers(projectStoreID) {
        axiosGlobal.get(`/api/getProjectMembers?projectStoreID=${projectStoreID}`).then((response) => {
            console.log(response)
            if (response['request']['status'] == 200) {
                let responseObj = response['data'];
                this.setState({
                    data: responseObj,
                    inviteeMail: ""
                })
            }
        })

    }

    //7/3/2023:balaji: currently used by delete 
    
    updateProjectMembers(userManagementStore, state) {
        if (userManagementStore.state != state) {
            userManagementStore.state = state;
            let userManagementStoreLocal = JSON.stringify(userManagementStore);
            console.log(userManagementStoreLocal);
            axiosGlobal.put(`/api/updateOrDeleteUser`, userManagementStoreLocal).then((response) => {
                console.log(response);
                if (response['status'] == 200 || response['status'] == 202) {
                    this.getProjectMembers(this.props.projectStoreID);
                }
            }).catch((error) => {
                console.log(error);
            });
        }
    }




    sentProjectInvite(inviteeMail) {
        // this.setState({inviteeMail:inviteeMail});
        axiosGlobal.post(`/api/addUserToProject?inviteeMail=${inviteeMail}&projectStoreID=${this.props.projectStoreID}`).then((response) => {
            console.log(response);
            if (response['status'] == 200 || response['status'] == 202) {
                this.getProjectMembers(this.props.projectStoreID);
            } else {
                this.setState({
                    inviteeMail: ""
                })
            }
        });
    }


    componentDidMount() {
        if (this.props.projectStoreID) {

            this.getProjectMembers(this.props.projectStoreID)
        }
    }


    render() {
       

        return <>
            <div className={this.props.themeid==1?" EmailBox darkEmailBox":"EmailBox liteEmailBox"}>
                <h6>Send project invite</h6>
                <div className="fullWidth">
                    <TextField fullWidth value={this.state.inviteeMail} onChange={(value) => { this.setState({ inviteeMail: value.target.value }) }} id="filled-basic" label="email" variant="filled" color='info' focused />
                    <Button id="muibutton" variant="contained" onClick={(event) => {
                        this.sentProjectInvite(this.state.inviteeMail)
                    }}>send</Button>
                </div>
            </div>
            {
                this.state.data.map((data, index) =>
                    <div key={index} className={this.state.actionBarOpenFor === data.userStore.userStoreID ? "userblocks ubHeight" : "userblocks"}>
                        <div>
                            <div className="actionLayout">
                                <div className="userName">
                                    {data.userStore.userName}
                                    {/* {data.admin && " -adminstrator"} */}
                                </div>
                                <div className="activator" onClick={() => { this.setState({ actionBarOpenFor: this.state.actionBarOpenFor === data.userStore.userStoreID ? 0 : data.userStore.userStoreID }) }}>
                                    {this.state.actionBarOpenFor === data.userStore.userStoreID ? "v" : ">"}
                                </div>
                            </div>
                            <div className="userDetails">
                                {data.userStore.linkedEmail}
                            </div>
                            {
                                this.state.actionBarOpenFor === data.userStore.userStoreID &&
                                <div className="actionArea">
                                    <div className="actionButton">{ !data.admin &&
                                        <div onClick={() => { 
                                            this.setState({ actionBarOpenFor:0});
                                            this.updateProjectMembers(data, 4) }}>
                                            delete
                                        </div>
    }
                                    </div>
                                </div>
                            }
                        </div>
                    </div>
                )
            }
        </>;
    }
}


export default ManageUser;