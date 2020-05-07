import React, {Component} from 'react';
import {SensorContext} from "../contexts/sensorContext";
import './sensor.css';


/**
 *
 * @author :  Jayagoda N.M.
 *           IT17184304
 *
 */

class Sensor extends Component {
    render() {
        const {  floorNum, roomNum, alertStatus } = this.props;
        return (
            <SensorContext.Consumer>{( sensorContext ) => {
                const { onClickSensor } = sensorContext;
                let styleVal;
                if (alertStatus == 'GREEN' ){
                    styleVal = {
                        backgroundColor: "#008900"
                    }
                } else if( alertStatus == 'RED') {
                    styleVal = {
                        backgroundColor: "#970000"
                    }
                } else{
                    styleVal = {
                        backgroundColor: "#b5b5b5",
                        color : "#000"
                    }
                }
                return(
                    <button type="button" className="list-group-item list-group-item-action card-body card-title" onClick={() => onClickSensor(floorNum, roomNum)}
                        style={styleVal}>
                        Sensor  floor : {floorNum}  &nbsp;&nbsp;  Room : {roomNum}
                    </button>
                );
            }}</SensorContext.Consumer>
        );
    }
}

export default Sensor;