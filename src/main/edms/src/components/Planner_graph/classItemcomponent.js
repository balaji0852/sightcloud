import React, { Component, useEffect, useReducer, useState } from 'react';
import "./class_item_component.css";
import { BsThreeDotsVertical } from "react-icons/bs";
import { axiosGlobal as axios } from '../interaction/one_instance';
import ErrorBoundary from '../Pages/loaders/error_boundary';
import { Link, Switch, Route } from 'react-router-dom';
import CommentSectionPage from '../Pages/comment_section_page';
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import { colors } from "../authentication/server-state";

//balaji-adding n€w ft
import MenuItem from "@material-ui/core/MenuItem";
import Menu from "@material-ui/core/Menu";
import { axiosGlobal} from '../interaction/one_instance';




const ClassItemComponent = ({doQuery, dispatch, classMaster, title, description, itemClassColorID, itemMasterID
    , state,lastComment,lastCommentedUser }) => {

    const [comment, setComments] = useState('loading');
    const [classRoute, setClassRoute] = useState('/class');
    useEffect(() => {
        setClassRoute(`/classes/${itemMasterID}`);
        // axios.get(`api/dataInstanceMaster/lastComment?itemMasterID=${itemMasterID}`).then((response) => {
             let comment;
        //     try {
            lastComment = lastComment != undefined
                    && lastComment.length != 0
                    ? lastComment : "no comments, yet...";
        //     } c atch (Error) {
        //         comment = Error;
        //     }

        //     //this.setState({
             setComments(lastComment);
        //     //});
        // })
    }, [itemMasterID,lastComment]);


    const [anchorEl, setAnchorEl] = React.useState(null);

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleClick = (event,classMaster) => {
        setAnchorEl(event.currentTarget);
    };


    const handleEdit=()=>{
        let stateLocal = state;
        stateLocal.setForEdit = true;
        stateLocal.classMaster = classMaster;
        dispatch({
            type: 'changeClass', payload: state
        });
       
        window.location.href = '#/dashboard/add';
        handleClose();
    }


    const handleDelete=()=>{
        handleClose();
        axiosGlobal.delete(`api/classMaster/${itemMasterID}`).then((response) => {
            if (response["data"] === 'success') {
                doQuery(itemMasterID); 
            }
          }).catch((response) => {
  
  
          });
    }



    return (
        <ErrorBoundary>
            <div className="classItemComponent " style={{ backgroundColor: colors.at(itemClassColorID) }}>
           
                <div className='topItems'>
                    <div className='h4'>{title}</div>
                    <BsThreeDotsVertical className='editIcon' size={40} onClick={handleClick} />
                        <Menu
                            keepMounted
                            anchorEl={anchorEl}
                            onClose={handleClose}
                            open={Boolean(anchorEl)}
                        >
                            <MenuItem onClick={handleEdit}>edit</MenuItem>
                            <MenuItem onClick={handleDelete.bind(this)}>delete</MenuItem>
                        </Menu>
                </div>
                <div className='description limitingText'>{description}</div>

                <Link to={classRoute} onClick={() => {
                    let stateLocal = state;
                    stateLocal.classMaster = classMaster;
                    dispatch({
                        type: 'changeClass', payload: stateLocal
                    })
                }}>
                    <div className='lastComment limitingText' >{lastComment}</div>
                </Link>
                <div className='lastCommentCasing'>
                {lastCommentedUser.photoURL!="empty" &&  <img className="dataInstancesUserIcon" src={lastCommentedUser.photoURL}/>}
                </div>

            </div>
        </ErrorBoundary>);
    // }
}//props.itemClassColorID


ClassItemComponent.propTypes = {
    classMaster: PropTypes.object.isRequired,
    itemMasterID: PropTypes.any.isRequired,
    title: PropTypes.any.isRequired,
    description: PropTypes.any.isRequired,
    itemClassColorID: PropTypes.any.isRequired,
    state: PropTypes.any.isRequired,
    doQuery:PropTypes.any.isRequired,
    lastComment:PropTypes.any.isRequired,
    lastCommentedUser:PropTypes.any.isRequired
}


const mapStateToProps = (state) => {

    return ({
        state: state
    });
};


export default connect(mapStateToProps)(ClassItemComponent);


