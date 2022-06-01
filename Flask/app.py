from flask import Flask

app = Flask(__name__)

@app.route('/')
def index():
    return 'Welcome!'

@app.route('/index')
def index():
    return 'Welcome My Friend!'


if __name__ == '__main__':
    app.run()
