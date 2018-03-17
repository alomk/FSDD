from flask import Flask, render_template
from flask_bootstrap import Bootstrap

app = Flask(__name__)

@app.route("/")
@app.route("/index")
def index():
    return render_template('index.html')

@app.route("/search", methods=['GET', 'POST'])
def search():
    return render_template('search.html')

@app.route("/fresh")
def fresh():
    return render_template('fresh.html')

@app.route("/deliver")
def deliver():
    return render_template('deliver.html')

@app.route("/stock")
def stock():
    return render_template('stock.html')
