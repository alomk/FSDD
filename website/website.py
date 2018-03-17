from flask import Flask, render_template
from flask_bootstrap import Bootstrap
from flask_login import LoginManager

app = Flask(__name__)

login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = "users.login"
login_manager.login_message = u"hello"

@login_manager.user_loader
def load_user(user_id):
    return User.get(user_id)

@app.route("/")
@app.route("/index")
@app.route("/logout")
def index():
    return render_template('index.html')

@app.route("/search", methods=['GET', 'POST'])
def search():
    return render_template('search.html')

@app.route("/fresh")
def fresh():
    return render_template('fresh.html')

@app.route("/expire")
def deliver():
    return render_template('expire.html')

@app.route("/stock")
def stock():
    return render_template('stock.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
    return render_template('login.html')

@app.errorhandler(404)
def page_not_found(e):
    return render_template('404.html'), 404
