function zeroFirstFormat(value)
{
    if (value < 10)
    {
        value='0'+value;
    }
    return value;
}

function dateTime()
{
    let currentDatetime = new Date();
    let hours = zeroFirstFormat(currentDatetime.getHours());
    let minutes = zeroFirstFormat(currentDatetime.getMinutes());
    let seconds = zeroFirstFormat(currentDatetime.getSeconds());

    return hours+":"+minutes+":"+seconds;
}
document.getElementById('clock').innerHTML = dateTime();
setInterval(function () {
    document.getElementById('clock').innerHTML = dateTime();
}, 5000);