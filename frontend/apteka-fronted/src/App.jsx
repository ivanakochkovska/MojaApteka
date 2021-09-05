import React from "react"
import './App.css';
import {Component} from "react";
import {Header} from "./components/Header";
import {DrugTable} from "./components/DrugTable";
import DrugTableTest from "./components/DrugTableTest";


class App extends Component {
  render() {
    return (
        <div>

          <Header/>
          {/*<DrugTable/>*/}
          <DrugTableTest/>
        </div>


    )
  }

}


export default App;

