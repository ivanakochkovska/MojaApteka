import React from "react"
import './App.css';
import {Component} from "react";
import {Header} from "./components/Header";
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import DrugTableTest from "./components/DrugTableTest";
import ShowDrugDetails from "./components/ShowDrugDetails";


class App extends Component {
  render() {
    return (
        <Router>
        <div>

          <Header/>

          <Switch>
              <Route exact path="/" component={DrugTableTest}/>
              <Route exact path="/drug" component={ShowDrugDetails}/>
          </Switch>

        </div>
        </Router>


    )
  }

}


export default App;

