
import React, {Component} from "react";
import axios from "axios";
import {Link} from "react-router-dom";

class DrugTableTest extends Component {

    constructor(props) {
        super(props);
        this.state={
            query: "",
            data: [],
            filteredData: [],
        }
    }


    handleInputChange = event => {
        const query = event.target.value;

        this.setState(prevState => {
            const filteredData = prevState.data.filter(element => {
                return element.brandName.toLowerCase().includes(query.toLowerCase());
            });

            return {
                query: query,
                filteredData: filteredData,
            };
        });
    };

    getData = () => {
        axios.get('/drugs/overview')
            //.then(response => response.json())
            .then(response => {
                const { query } = this.state;
                const filteredData = response.data.filter(element => {
                    return element.brandName.toLowerCase().includes(query.toLowerCase());
                });

                this.setState({
                    data: response.data,
                    filteredData: filteredData,
                });
            });
    };

    componentWillMount() {
        this.getData();
    }
    refresh(){
        window.location.reload(true)
    }

    formatPrice(price) {
        return price.toString().substr(0, price.indexOf('.'));
}

    render() {
        return (
            <div className="searchForm" >
                <div className="input-group input-group-lg">
                        {/*<input type="text" className="form-control" aria-label="Large"*/}
                        {/*       aria-describedby="inputGroup-sizing-sm" placeholder={"Пребарај лек по име, генеричко име или цена"}*/}
                        {/*    placeholder="Пребарај книги"*/}
                        {/*    value={this.state.query}*/}
                        {/*    onChange={this.handleInputChange}*/}
                        {/*/>*/}

                </div>


                <>
                    <div>

                    </div>
                    <div style={{paddingLeft: "200px", paddingRight: "200px", paddingTop: "100px" }}>
                        <h1>Преглед на лекови</h1>
                        <br/>
                        <div className="searchForm" >
                            <div className="input-group input-group-lg">
                                <input type="text" className="form-control" aria-label="Large"
                                       aria-describedby="inputGroup-sizing-sm" placeholder={"Пребарај лек по име"}
                                       value={this.state.query}
                                       onChange={this.handleInputChange}
                                />
                            </div>

                            </div>
                        <br/>
                        <table className="table">
                            <thead>
                            <tr>
                                <th>Име</th>
                                <th>Генеричко име</th>
                                <th>Цена</th>
                                <th>Детали</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.filteredData.map( drug =>
                                    <tr id={drug.urlId}>
                                        <td>{drug.brandName}</td>
                                        <td>{drug.genericName}</td>
                                        <td>{this.formatPrice(drug.price)} DEN - MKD</td>
                                        <td>
                                            <Link to={{
                                                pathname: "/drug",
                                                state: {
                                                    drugId: drug.urlId,
                                                }
                                            }
                                            }>
                                            <button className="btn btn-primary btn-sm" >Преглед
                                            </button>
                                            </Link>
                                        </td>
                                    </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </>
            </div>

        );
    }



}
export default DrugTableTest;