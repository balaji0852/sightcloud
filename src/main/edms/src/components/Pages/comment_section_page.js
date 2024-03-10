import React, { useEffect, useState } from "react";
import { BsArrowLeft } from "react-icons/bs";
import "./comment_section_page.css";
import PlannerGraph from '../Planner_graph/planner_graph';
import { connect } from 'react-redux'
import PropTypes from 'prop-types';
import { axiosGlobal as axios } from '../interaction/one_instance';
import EditBox from "../Planner_graph/EditBox";
import { classMaster as classMasterEmpty } from "../authentication/server-state";
const CommentSectionPage = ({ itemMasterID,
    description,
    itemName,
    itemClassColorID,
    state,
    dispatch }) => {


    //balaji: made itemMasterIDLocal to global, for pg's need.
    const URL = window.location.toString();
    const itemMasterIDLocal = URL.substring(URL.lastIndexOf('/') + 1);
    const [classMaster, setClassMaster] = useState({
        'itemMasterID': itemMasterID,
        'description': description,
        'itemName': itemName,
        'itemClassColorID': itemClassColorID
    });
    const [editBoxState, SetEditBoxState] = useState(false);
    const [pgReRender, setPGReRender] = useState(1);
    const [showdescription, setDescription] = useState(false);
    const [themeCSS,setthemeCSS] = useState({
        color:state['userStore']['themeID']==1 ?"White":"Black",
        backgroundColor:state['userStore']['themeID']==1 ?"Black":"White",
    })
    
    //balaji:fix for handling empty props
    useEffect(() => {
        if (itemMasterID === 'empty' || itemMasterID === 0) {

            setClassMaster({
                'itemMasterID': itemMasterIDLocal,
                'description': 'loading...',
                'itemName': 'loading...',
                'itemClassColorID': 0
            });


            axios.get(`api/classMaster/id?itemMasterID=${itemMasterIDLocal}`).then((response) => {
                try {
                    if (response['status'] === 200 && response['data'].length != 0) {
                        console.log('pass');
                        setClassMaster({
                            'itemMasterID': response['data']['itemMasterID'],
                            'description': response['data']['description'],
                            'itemName': response['data']['itemName'],
                            'itemClassColorID': response['data']['itemClassColorID']
                        });
                    } else {
                        setClassMaster({
                            'itemMasterID': 'empty',
                            'description': 'empty',
                            'itemName': 'empty',
                            'itemClassColorID': 'empty'
                        });
                    }
                } catch (Error) {
                    setClassMaster({
                        'itemMasterID': 'empty',
                        'description': 'empty',
                        'itemName': 'something went wrong :(, try again...',
                        'itemClassColorID': 'empty'
                    });
                }
            }).catch((response) => {
                setClassMaster({
                    'itemMasterID': 'empty',
                    'description': 'empty',
                    'itemName': 'something went wrong :(, try again...',
                    'itemClassColorID': 'empty'
                });
            });
        }
    }, [itemMasterID, itemName, itemMasterIDLocal])

    // constructor(props) {
    //     super(props);


    //     this.state = {
    //         'PATHVARIABLE': undefined,
    //         'CLASSMASTER': {}
    //     }


    // }

    //componentDidMount() {
    // console.log('this');
    // let store = createStore(appReducer);
    // console.log(store.getState());

    // store.subscribe(() => 

    //     console.log('working')

    // );

    // let { id } = this.props.match.params;
    // this.setState({
    //     'PATHVARIABLE': id
    // });
    //}


    // render() {

    const handleOnclose = () => {
        // SetEditBoxState(false);
        // console.log(`oopz `, IsEmptyClose);
        // if (!IsEmptyClose) {
        //     console.log(IsEmptyClose);
        //     setPGReRender(Math.random());
        // }

        //11/11/2022- balaji - prob -adding ,need global classMaster cleanup in csp wide 
        let stateLocal = state;
        stateLocal.classMaster = classMasterEmpty;
        stateLocal.openDialog = false;
        stateLocal.hourlyDataInstanceFromChild = []
        stateLocal.type = 0;
        stateLocal.pgReRender = 0;
        dispatch({
            type: 'changeGraphDialog',
            payload: stateLocal
        });
    }


    const handleGD = () => {
        //8/11/2022-balaji - adding classMaster state chng, 
        //and type and pgReRender
        let stateLocal = state;
        let classMasterLocal = classMaster;
        //10/11/2022-balaji prob- adding classMaster injection.

        classMasterLocal['itemMasterID'] = classMaster['itemMasterID'];
        classMasterLocal['description'] = classMaster['description'];
        classMasterLocal['itemName'] = classMaster['itemName'];
        classMasterLocal['itemClassColorID'] = classMaster['itemClassColorID'];
        classMasterLocal['categoryID'] = 0;
        classMasterLocal['subCategoryID'] = 0;
        classMasterLocal['itemPriority'] = 0;
        classMasterLocal['isItemCommentable'] = 0;

        stateLocal.classMaster = classMaster;
        stateLocal.openDialog = true;
        stateLocal.hourlyDataInstanceFromChild = []
        stateLocal.type = 1;
        stateLocal.pgReRender = 0;


        dispatch({
            type: 'changeGraphDialog',
            payload: stateLocal
        });
    }
    console.log(themeCSS)
    return (<div 
        // className='project'
    style={themeCSS}>
        <div className='head'  style={themeCSS}>
            <div className='header'>
                {/* <Link className="link" to='/dashboard/home'> */}
                {/* onClick={()=> window.location.href='#/dashboard/home'} */}
                <BsArrowLeft className="icon" size={40} onClick={() => {
                    window.location.href = "#/dashboard/class";
                    handleOnclose()
                }} />
                {/* </Link> */}
                {/* <h1>{this.props.classMaster['itemName']}</h1> */}
                <h1 className="h1">{classMaster.itemName}</h1>

            </div>
        </div>
        <div className="PlannerGraph">
            <PlannerGraph graphType={1} test={state['pgReRender']} itemMasterID={itemMasterIDLocal} />
        </div>
        <div className="floating-button"

            onClick={() => {
                handleGD();
            }}>
            +
        </div>


        {/* {editBoxState && <EditBox editBoxState={editBoxState}
            //balaji:sig-55, need a type for re usability of editbox
            type={2}
            onClick={handleOnclose}

            //balaji : adding the state, we need the valsidebar state, 
            state={state}

            itemMasterID={classMaster['itemMasterID']}
            itemName={classMaster.itemName}
            description={classMaster.description} />
        } */}


    </div>);
    // }
}




// const mapStateToProps = state => ({
//     classMaster:state
// })


CommentSectionPage.propTypes = {
    itemMasterID: PropTypes.any.isRequired,
    description: PropTypes.any.isRequired,
    itemName: PropTypes.any.isRequired,
    itemClassColorID: PropTypes.any.isRequired,
    state: PropTypes.any.isRequired
}


export default CommentSectionPage;

//   export default connect(
//     mapStateToProps
//   )(CommentSectionPage)