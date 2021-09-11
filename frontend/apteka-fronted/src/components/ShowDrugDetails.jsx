
import React, {Component} from "react";
import axios from "axios";

class ShowDrugDetails extends Component {

    constructor(props) {
        super(props);
        this.state={
            brandName: "",
            genericName: "",
            price: "",
            strength: "",
            manufacturer: "",
            dosageForm: "",
            similarDrugs: [],
        }
    }


    getData = () => {
        axios.get('/drug', {
            params: {
                id: this.props.location.state.drugId
            }
        })
            //.then(response => response.json())
            .then(response => {
                this.setState({
                    brandName: response.data.brandName,
                    genericName: response.data.genericName,
                    price: response.data.price,
                    strength: response.data.strength,
                    manufacturer: response.data.manufacturer,
                    dosageForm: response.data.dosageForm,
                    similarDrugs: response.data.similarTo,
                })
            });
    };

    componentDidMount() {
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
                <>
                    <div>
                        <div className="card" style={{paddingLeft: "200px", paddingRight: "200px", paddingTop: "100px" }}>
                            <div className="card-header">
                                {this.state.brandName}
                            </div>
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item"><b>Генеричко име: </b>{this.state.genericName}</li>
                                <li className="list-group-item"><b>Цена: </b>{this.formatPrice(this.state.price)} DEN - MKD</li>
                                <li className="list-group-item"><b>Јачина: </b>{this.state.strength}</li>
                                <li className="list-group-item"><b>Производител: </b>{this.state.manufacturer}</li>
                                <li className="list-group-item"><b>Дозирање: </b>{this.state.dosageForm}</li>
                            </ul>
                        </div>
                    </div>
                    <div>

                    </div>
                    <div style={{paddingLeft: "200px", paddingRight: "200px", paddingTop: "100px" }}>
                        <h1>Слични лекови: </h1>
                        <br/>
                        <br/>
                        <table className="table">
                            <thead>
                            <tr>
                                <th>Име</th>
                                <th>Генеричко име</th>
                                <th>Цена</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.similarDrugs.map( drug =>
                                    <tr id={drug.urlId}>
                                        <td>{drug.brandName}</td>
                                        <td>{drug.genericName}</td>
                                        <td>{this.formatPrice(drug.price)} DEN - MKD</td>
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
export default ShowDrugDetails;