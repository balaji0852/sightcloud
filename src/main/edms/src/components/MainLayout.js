import React,{Component}  from 'react';
import  NavMenu  from './NavBar/NavMenu';

class MainLayout extends Component {


  render () {
    return (
        <NavMenu >
          {this.props.children}
        </NavMenu>
    );
  }
}

export default MainLayout;
