import React, { Component } from "react";
import './common_descriptor.css';

class CommonDescriptor extends Component {

    constructor(props) {
        super(props);
    }


    render() {

        return (<div className="main">
            <img className='loadingDescriptionImage'
                src={this.props.status=='contains' || this.props.status==''?this.props.loadingDescription:this.props.failureLoadingDescription} />
            <h5 className="d">{this.props.status=='contains'?this.props.description:this.props.failureDescription}</h5>
        </div>);
    }
}


export default CommonDescriptor;

