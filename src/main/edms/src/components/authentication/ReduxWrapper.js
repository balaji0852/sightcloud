import { Component,React } from "react";
import { connect } from 'react-redux'
import PropTypes from 'prop-types';




class ReduxWrapper extends Component {


    componentDidMount(){
        if(this.props.state['projectStoreID']===0){
            window.location.href = '#/projects';
        }
    }


    render() {
        return <>
            {this.props.children}
        </>;
    }
}



ReduxWrapper.propTypes = {
    state: PropTypes.object.isRequired
}

const mapStateToProps = (state) => {
    return ({
        state: state
    });
};


export default connect(mapStateToProps)(ReduxWrapper);