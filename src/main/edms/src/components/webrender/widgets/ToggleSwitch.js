import React, { useState } from "react";
import "./ToggleSwitch.css";

const ToggleSwitch = ({ id, onChange ,checked}) => {


    
return (
	<div className="container">
	<div className="toggle-switch">
		<input type="checkbox" className="checkbox"
			name={id}
             id={id}
			 checked={checked}
             onChange={(event)=>{onChange(event)}}
             />
		<label className="label" htmlFor={id}>
		<span className="inner" />
		<span className="switch" />
		</label>
	</div>
	</div>
);
};

export default ToggleSwitch;
