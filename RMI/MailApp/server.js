const express = require('express');
const bodyparser = require('body-parser');
const nodemailer = require('nodemailer');

const app = express();

app.use(bodyparser.urlencoded({extended: false}));
app.use(bodyparser.json());

app.post('/sendEmail', (req,res) => {

    const floor = req.body.floor_no;
    const room = req.body.room_no;
    const email = req.body.email;
    const sender = 'firealarm.monitoring.system@gmail.com';

    let transport = nodemailer.createTransport({
        host: 'smtp.gmail.com',
        port: 587,
        auth: {
            user: sender,
            pass: 'firealarm123'
        }
    });

    const message = {
        from: sender,
        to: email,
        subject: 'Fire Alarm Notify',
        text: 'Our system detected the Room '+room+' in Floor '+floor+' has critical issue with firing. Please be careful and follow the rules while our services fix it. Thank you'
    };

    transport.sendMail(message, function(err, info) {
        if (err) {
            console.log(err)
        } else {
            console.log(info);
            res.json(info.response);
        }
    });

});


app.listen(2500, () => console.log('Mail Server is Started..'));
