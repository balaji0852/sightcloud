import React, { useEffect ,useState } from 'react';
import { Link } from 'react-router-dom';
import { useHistory } from 'react-router-dom'
import "./BreadCrumb.css"

const BreadCrumb = (props)=>{

    const history = useHistory() 
    const [path, updatePath] = useState();
    useEffect((props) => {
       return history.listen((location) => { 
          updatePath(location.pathname);
          props.updatePath(location.pathname);
       }) 
    },[history]) 


    console.log('bc');
    console.log(path===undefined);
 

    if(path===undefined){
      return <div className='BreadCrumb'><Link >/dashboard</Link></div>;
    }
    return <div className='BreadCrumb'><Link >{path}</Link></div>;
}


export default BreadCrumb;