import React, { useEffect ,useState } from 'react';
import { Link } from 'react-router-dom';
import { useHistory } from 'react-router-dom'
import "./BreadCrumb.css"

const BreadCrumb = ({updatePathThroughProp})=>{

    const history = useHistory() 
    const [path, updatePath] = useState();
    useEffect(() => {
         history.listen((location) => { 
          updatePath(location.pathname);
       }) 
       updatePathThroughProp(path);
    },[history,updatePathThroughProp,path]) 


    

    if(path===undefined){
      return <div className='BreadCrumb'><Link >/dashboard</Link></div>;
    }
    return <div className='BreadCrumb'><Link >{path}</Link></div>;
}


export default BreadCrumb;