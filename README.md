# Max backend

This is the backend for the Max project. It is a Flask application that provides a REST API for the frontend.

## Installation
1. Change into the project directory:

    ```bash
    cd yourproject
    ```

2. Create a virtual environment:

    ```bash
    python3 -m venv venv
    ```
3. Install the required packages:

    ```bash
    pip install -r requirements.txt
    ```

Alternatively, you can use IDE such as PyCharm to install the required packages.


## Usage
1. First authorize with Google Cloud:
    ```bash
    gcloud auth application-default login
    ```
2. Start the Flask application:
    ```bash
    python main.py
    ```