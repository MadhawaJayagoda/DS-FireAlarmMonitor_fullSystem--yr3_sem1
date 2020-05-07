import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import 'bootstrap/dist/css/bootstrap.css';
import SensorContextProvider from "./contexts/sensorContext";

/**
 *
 * @author :  Jayagoda N.M.
 *           IT17184304
 *
 */

ReactDOM.render(
    <React.StrictMode>
        <SensorContextProvider>
            <App/>
        </SensorContextProvider>
    </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
