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
    let newx = (x - 175) / (sizeOfSquare);
    let newy = (175 - y) / (sizeOfSquare);
    console.log("sizeOfSquare: " + sizeOfSquare)
    console.log("marginOfGraph: " + marginOfGraph)
    console.log("margin: " + margin)
    console.log("y: " + y)

    console.log("ddddd")

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

    setResultFromGraph(
        [
            {
                name: "x",
                value: newx.toString()
            },
            {
                name: "y",
                value: newy.toString()
            },
            {
                name: "r",
                value: r.toString()
            }
        ]
    )

})

function setDots() {
    let items = document.getElementById("table_data").childNodes
    items[0].childNodes.forEach((e) => {

        if (e.childNodes[0].childNodes.length > 0 && e.childNodes[0].classList.value == "result") {
            setDot(x, y, r, e.childNodes[0].childNodes[0])
        }
    });
}

function setDot(x, y, r, result) {
    let rad = 2;

    if (result.textContent.trim() == "HIT") {
        svg.append("circle")
            .attr("class", "dot")
            .attr("cx", x)
            .attr("cy", y)
            .attr("r", rad);
    } else {
        svg.append("circle")
            .attr("class", "error_dot")
            .attr("cx", x)
            .attr("cy", y)
            .attr("r", rad);
    }
}