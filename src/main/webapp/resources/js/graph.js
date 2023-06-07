"use strict"


let height = 350,
    width = 350,
    margin = 30,
    radius = 0;
let x, y, r;

const xAxisLength = width - 2 * margin;
const yAxisLength = height - 2 * margin;

const svg = d3.select("div.module-graph").append("svg")
    .attr("class", "graph")
    .attr("id", "graph")
    .attr("width", width)
    .attr("height", height);


const scaleX = d3.scaleLinear()
    .domain([-5, 5])
    .range([0, xAxisLength]);

const scaleY = d3.scaleLinear()
    .domain([5, -5])
    .range([0, yAxisLength]);


const xAxis = d3.axisBottom(scaleX);
const yAxis = d3.axisLeft(scaleY);


svg.append("g")
    .style("fill", "none")
    .attr("class", "x-axis")
    .attr("transform",
        "translate(" + margin + "," + height / 2 + ")")
    .call(xAxis);

svg.append("g")
    .style("fill", "none")
    .attr("class", "y-axis")
    .attr("transform",
        "translate(" + width / 2 + "," + margin + ")")
    .call(yAxis);
//круг

const sizeOfSquare = xAxisLength / 10;
const arcGenerator = d3.arc()
    .outerRadius(sizeOfSquare * radius / 2)
    .innerRadius(0)
    .startAngle(0)
    .endAngle(Math.PI / 2);
const sector = svg.append("path")
    .attr("class", "sector")
    .attr("id", "sector")
    .attr("transform", "translate(175,175)")
    .attr("d", arcGenerator());
//прямоугольник
const rect = svg.append("rect")
    .attr("class", "rect")
    .attr("id", "rect")
    .attr("x", 175)
    .attr("y", 175)
    .attr("width", sizeOfSquare * radius / 2)
    .attr("height", sizeOfSquare * radius);
let x1 = 175 - sizeOfSquare * radius;
let y2 = 175 + sizeOfSquare * radius;
//треугольник
const triangle = svg.append("polygon")
    .attr("class", "triangle")
    .attr("id", "triangle")
    .attr("points", x1 + ",175" + " 175,175 " + "175," + y2);


d3.selectAll("g.x-axis g.tick")
    .append("line")
    .classed("grid-line", true)
    .attr("x1", 0)
    .attr("y1", 0)
    .attr("x2", 0)
    .attr("y2", xAxisLength / 2);
d3.selectAll("g.x-axis g.tick")
    .append("line")
    .classed("grid-line", true)
    .attr("x1", 0)
    .attr("y1", 0)
    .attr("x2", 0)
    .attr("y2", -xAxisLength / 2);

d3.selectAll("g.y-axis g.tick")
    .append("line")
    .classed("grid-line", true)
    .attr("x1", 0)
    .attr("y1", 0)
    .attr("x2", yAxisLength / 2)
    .attr("y2", 0);
d3.selectAll("g.y-axis g.tick")
    .append("line")
    .classed("grid-line", true)
    .attr("x1", 0)
    .attr("y1", 0)
    .attr("x2", -yAxisLength / 2)
    .attr("y2", 0);


function changeSize() {
    radius = document.getElementById("form:r_hinput").value
    x1 = 175 - sizeOfSquare * radius;
    y2 = 175 + sizeOfSquare * radius;
    rect.attr("width", sizeOfSquare * radius / 2)
        .attr("height", sizeOfSquare * radius);
    triangle.attr("points", x1 + ",175" + " 175,175 " + "175," + y2);
    svg.selectAll("path.sector").remove()
    svg.selectAll("circle.dot").remove()
    svg.selectAll("circle.error_dot").remove()

    const arcGenerator = d3.arc()
        .outerRadius(sizeOfSquare * radius / 2)
        .innerRadius(0)
        .startAngle(0)
        .endAngle(Math.PI / 2);
    svg.append("path")
        .attr("class", "sector")
        .attr("id", "sector")
        .attr("transform", "translate(175,175)")
        .attr("d", arcGenerator());

}

document.querySelector(".graph").addEventListener(("click"), (event) => {
    let marginOfGraph = window.getComputedStyle(document.querySelector('body')).margin.slice(0, -2)
    x = event.clientX - marginOfGraph;
    y = event.clientY - marginOfGraph;
    r = document.getElementById("form:r_hinput").value
    if (r < 2 || r > 5) {
        new Toast({
            title: false,
            text: 'r must be between 2 and 5',
            theme: 'danger',
            autohide: true,
            interval: 10000
        });
        return;
    }
    if (y < 30 || y > height - margin || x < 30 || x > width - margin) {
        return;
    }

    setDot(event.clientX, event.clientY, r)
    console.log("execute method on server side", getUserX(x).toString(), getUserY(y).toString(),  r.toString())
    setResultFromGraph(
        [
            {
                name: "x",
                value: getUserX(x).toString()
            },
            {
                name: "y",
                value: getUserY(y).toString()
            },
            {
                name: "r",
                value: r.toString()
            }
        ]
    )

})

function setDot(x, y, r) {
    let rad = 2;
    console.log(x, y, r)

    if (isHIT(getUserX(x), getUserY(y), r)) {
        console.log("green")
        svg.append("circle")
            .attr("class", "dot")
            .attr("cx", x)
            .attr("cy", y)
            .attr("r", rad);
    } else {
        console.log("red")
        svg.append("circle")
            .attr("class", "error_dot")
            .attr("cx", x)
            .attr("cy", y)
            .attr("r", rad);
    }
}

function getUserX(x) {
    return (x - 175) / (sizeOfSquare)
}

function getUserY(y) {
    return (175 - y) / (sizeOfSquare)
}

function isHIT(x, y, r) {
    return isInRectangle(x, y, r) || isInTriangle(x, y, r) || isInSector(x, y, r)
}

function isInTriangle(x, y, r) {
    return between(x, -r, 0) && between(y, -r - x, 0);

}

function between(x, left, right) {
    return x >= left && x <= right;
}

function isInRectangle(x, y, r) {
    return between(x, 0, r / 2) && between(y, -r, 0);
}

function isInSector(x, y, r) {
    return between(x, 0, r / 2) && between(y, 0, Math.sqrt(Math.pow(r / 2, 2) - Math.pow(x, 2)));
}
