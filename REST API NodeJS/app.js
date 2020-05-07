const express = require('express');
const bodyParser = require('body-parser');
var mysql = require('mysql');
var cors = require('cors');
const app = express();

app.use(bodyParser.json());
app.use(cors());

/**
 *
 * @author :
 *     Jayagoda N.M.
 *     IT17184304
 *
 */

//Database connection to the MySQL database
var connection = mysql.createConnection({
    host : 'localhost',
    database : 'fire_alarm',
    user : '< --username-- >',
    password : '< --password-- >'
});

connection.connect(function(err){
   if (err){
       console.error('Error connecting to the Database : ' + err);
       return;
   }

    console.log("Connected to the MySQL database successfully..! ");
});


//routes

//Get All the Sensor Data
app.get('/sensorData', async (req, res) => {
    const queryString = 'SELECT * FROM sensors';
    connection.query(queryString, function (err, rows) {
        if(err){
            console.log("Error : ", err);
            return;
        }
        res.send(rows);
    });
});

//Add sensor data to the Database
app.post('/sensorRecord', async(req, res) => {
    const floor_no = req.body.floor_no;
    const room_no = req.body.room_no;
    const smoke_level = req.body.smoke_level;
    const carbondioxide_level = req.body.carbondioxide_level;
    const sensor_status = req.body.sensor_status;
    const owner_name = req.body.owner_name;
    const email = req.body.email;
    const phone_number = req.body.phone_number;

    var alert_status = '';

    if(sensor_status !== 'OFFLINE'){
        //Generating the alert_status record based on the smoke level and the carbondioxide levels, if the sensorStatus is 'ACTIVE'
        if( smoke_level > 5 || carbondioxide_level > 5 ){
            alert_status = 'RED';
        }else{
            alert_status = 'GREEN';
        }
    }

    const queryString = "INSERT INTO fire_alarm.sensors (floor_no, room_no, smoke_level, carbondioxide_level, sensor_status, alert_status, owner_name, email, phone_number) " +
        "VALUES (" + floor_no + ", " + room_no + ", " + smoke_level + ", " + carbondioxide_level + ", '" + sensor_status +"', '" + alert_status + "', '"
        + owner_name + "', '"+ email +"', '" + phone_number + "');";
    connection.query( queryString, function (err, rows) {
        if(err){
            console.error("Error : " + err);
            res.send(err);
            return;
        }
        res.json(rows);
    });
});

//Update sensor record of the Database
app.put('/sensorRecord', async(req, res) => {
    const floor_no = req.body.floor_no;
    const room_no = req.body.room_no;
    const smoke_level = req.body.smoke_level;
    const carbondioxide_level = req.body.carbondioxide_level;
    const sensor_status = req.body.sensor_status;

    var alertStatus = '';

    if(sensorStatus !== 'OFFLINE'){
        //Generating the alert_status record based on the smoke level and the carbondioxide levels, if the sensorStatus is 'ACTIVE'
        if( smokeLevel > 5 || carbondioxideLevel > 5 ){
            alertStatus = 'RED';
        }else{
            alertStatus = 'GREEN';
        }
    }

    let queryString =  "UPDATE fire_alarm.sensors " +
    "SET smoke_level = " + smoke_level + ", " +
        "carbondioxide_level = " + carbondioxide_level + ", " +
        "sensor_status = '" + sensor_status + "' , " +
        "alert_status = '" + alertStatus + "'  " +
    "WHERE floor_no = " + floor_no + " AND room_no = " + room_no + ";";

    connection.query( queryString, function (err, rows) {
        if(err){
            console.error("Error : " + err);
            res.send(err);
            return;
        }
        res.json(rows);
    });
});


//Get records by the sensorId
app.get('/sensorData/:sensor_id', async (req, res) => {
    const sensor_id = req.params.sensor_id;
    const  queryString = 'SELECT * FROM fire_alarm.sensors where sensor_id = ' + sensor_id;
    connection.query(queryString, function(err, rows){
        if(err){
            console.log("Error : ", err);
            return;
        }
        res.send(rows);
    });
});

//Get Records using floor number and the room number ; last record inserted - the latest update of the alert_status
app.get('/sensorData/:floor_no/:room_no', async (req, res) => {
    const floor_no = req.params.floor_no;
    const room_no = req.params.room_no;

    const queryString = 'SELECT room_no, smoke_level, carbondioxide_level, sensor_status, alert_status' + ' ' +
        'FROM fire_alarm.sensors' + ' ' +
        'WHERE floor_no = ' + floor_no + ' AND room_no = ' + room_no + ' ' +
        'ORDER BY sensor_id DESC' + ' ' +
        'LIMIT 1;';
    connection.query(queryString, function(err, rows) {
        if(err){
            console.log("Error : ", err);
            res.send(err);
            return;
        }
        res.send(rows);
    });
});

// Email service
app.post('/emailService/:floor/:room', async(req, res) => {
    const floor_no = req.params.floor;
    const room_no = req.params.room;

    const queryString = "SELECT email FROM fire_alarm.sensors WHERE floor_no =" + floor_no +  " AND room_no = " + room_no;

    connection.query(queryString, function (err, rows) {
        if(err){
            console.log("Error : ", err);
            res.send(err);
            return;
        }
        res.json(rows);
    });
});

//delete sensor data
app.delete('/sensorData/:sensor_id',(req,res) =>{
    const sensor_id = req.params.sensor_id;
    const  queryString = 'DELETE FROM fire_alarm.sensors where sensor_id = ' + sensor_id;
    connection.query(queryString, function(err, rows){
        if(err){
            console.log("Error : ", err);
            return;
        }
        res.send(rows);
    });
});

app.listen(3000);
