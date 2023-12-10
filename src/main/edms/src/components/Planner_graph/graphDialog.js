import { Component, React } from "react";
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import './graphDialog.css';
import EditBox from "./EditBox.js";
import { classMaster as classMasterEmpty } from "../authentication/server-state";


class GraphDialog extends Component {

    constructor(props) {
        super(props);

        this.clickedInsideENV = false;
    }

    closeDialog() {

        let stateLocal = this.props.state;
        // stateLocal.classMaster = classMasterEmpty;
        stateLocal.hourlyDataInstanceFromChild = [];
        stateLocal.openDialog = false;
        stateLocal.pgReRender = Math.random();
        stateLocal.type = 0;
        this.props.dispatch({
            type: 'changeGraphDialog',
            payload: stateLocal
        });
    }

    componentWillUnmount() {
        console.log('cwu');
    }
    render() {
        return <div className="dialog">{this.props.state['openDialog'] &&
            <div className={"grapDialog-background"} onClick={() => {
                if (!this.clickedInsideENV) {
                    this.closeDialog();
                }
                this.clickedInsideENV = false;
            }}>

                <div className="graphDialog" onClick={() => {
                    this.clickedInsideENV = true;
                }}>
                    <EditBox
                    //balaji - prob, editbox is redux connected,
                    //          we can avoid, state injection
                    //balaji - prob, we needed to have unmountable editbox
                    //          moved from display none to conditional rendering
                    // X->state={this.props.state}
                    />
                </div>
            </div>}
            <div className={this.props.state['valSideBar'] ? 'body body-width1' : 'body body-width'}>{this.props.children}</div>
        </div>;
    }

}

GraphDialog.propTypes = {
    state: PropTypes.object.isRequired
}

const mapStateToProps = (state) => {
    return ({
        state: state
    });
};

export default connect(mapStateToProps)(GraphDialog);