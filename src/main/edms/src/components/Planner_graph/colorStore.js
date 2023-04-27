import React, { Component } from 'react';
import './colorStore.css';
import { colors} from "../authentication/server-state";


class ColorStore extends Component {

    constructor(props) {
        super(props);
        this.state = {
        }
    }

    changeSelectedColor(event){
        this.props.onSelected(event);
        console.log(event);
        //this.setState({selectedColors:'green'})
    }


    render() {
        return <div className='colorStore'>
            {colors.map((Element,Index) => {
                    return <div key={Index} className={this.props.selectedColor==Element?' colorItem selectedColorItem':'colorItem'}  
                    onClick={this.changeSelectedColor.bind(this,Element)} style={{ backgroundColor: Element}}></div>;})
            }
        </div>;
    }
}


export default ColorStore;

