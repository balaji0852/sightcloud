import React, { Component } from 'react';
import { Route } from 'react-router';
import { HashRouter } from 'react-router-dom';
import Login from './components/authentication/login';
import MainLayout from './components/MainLayout.js';
import project from './components/Pages/project';
import projects from './components/Pages/project_list_page';
import GraphDialog from './components/Planner_graph/graphDialog';
import './custom.css';
//import  CommentSectionPage from "./components/Pages/comment_section_page";
import cspStore from './redux-store/comment_section_page_store';
import ProjectManagementPage from './components/webrender/Project_management_page';
import Website from './components/website/Website';

class App extends Component {

  render() {
    return (
      <GraphDialog>
        <HashRouter>
          <Route exact path='/' component={Website} />
          <Route exact path='/dev' component={Login} />
          <Route path='/dashboard' component={MainLayout} />
          <Route path='/project' component={project} />
          <Route path='/projects' component={projects} />
          <Route path="/classes/:id" component={cspStore} />
          <Route path="/pm" component={ProjectManagementPage} />
        </HashRouter>
      </GraphDialog>
    );
  }
}


export default App;