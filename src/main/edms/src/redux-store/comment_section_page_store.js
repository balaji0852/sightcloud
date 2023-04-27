import CommentSectionPage from "../components/Pages/comment_section_page";
import { connect } from 'react-redux';

const mapStateToProps = (state) => {
    

    //balaji: when reaching out page using url, empty props is crashing the app, 
    //we are nullfying the props, so the csp gets class data from get call.
    console.log(state)
    return ({
    state:state,
    description: state!=undefined?state['classMaster']['description']:'empty',
    itemMasterID: state!=undefined?state['classMaster']['itemMasterID']:'empty',
    itemName: state!=undefined?state['classMaster']['itemName']:'empty',
    itemClassColorID: state!=undefined?state['classMaster']['itemClassColorID']:'empty'
})};
  

export default connect(mapStateToProps)(CommentSectionPage);

