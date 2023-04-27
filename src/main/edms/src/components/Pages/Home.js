import React, { Component } from 'react';
import './Home.css';
import loadingDescription from './save-organisation.jpg';
//import ClassItemComponent from '../Planner_graph/classItemcomponent';
import PlannerGraph from '../Planner_graph/planner_graph';
import ReduxWrapper from '../authentication/ReduxWrapper';
class Home extends Component {

  render() {
    return (
      <ReduxWrapper>
      <div className='home'>
          
        {/* <div>
          <img className='loadingDescriptionImage'
            src={loadingDescription} />
          <h5 className='d'>Add you're task here, and collaborate efficiently along with you're team</h5>
        </div> */}
          <PlannerGraph id="2" graphType={2} test={999}/>
      </div>
      </ReduxWrapper>
    );
  }
}

export default Home;
