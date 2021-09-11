import React, {useEffect, useRef, useState} from "react";
import axios from "axios";

export const DrugTable = () =>  {


const [data, getData] = useState([]);

const [textState, setTextState] = useState('');
const [searchResults, setSearchResults] = React.useState([]);

    const formatPrice = (price) => {
        return price.toString().substr(0, price.indexOf('.'));
    }

    useEffect(()=> {
        axios.get('/drugs/overview').then( response => {
            getData(response.data);
            setSearchResults(response.data)
            console.log(response.data);
        });
    }, []);

    // useEffect(() => {
    //     const result = data.filter(drug => {
    //         drug.brandName.toLowerCase().includes(textState);
    //     });
    //
    //     //getData(result);
    //     console.log(result)
    // }, [textState])

    const handleInputChange = (event) => {
        //setTextState(event.target.value);
        console.log(event.target.value);
        const result = data.filter(drug => {
            drug.genericName.toLowerCase().includes(event.target.value);
        });

        getData(result);
        console.log(result);
    }

    // Hook
    function usePrevious(value) {
        // The ref object is a generic container whose current property is mutable ...
        // ... and can hold any value, similar to an instance property on a class
        const ref = useRef();

        // Store current value in ref
        useEffect(() => {
            ref.current = value;
        }, [value]); // Only re-run if value changes

        // Return previous value (happens before update in useEffect above)
        return ref.current;
    }
    return (
        <>
            <div>

                </div>
      <div style={{paddingLeft: "200px", paddingRight: "200px", paddingTop: "100px" }}>
          <h1>Преглед на лекови</h1>
          <br/>
          <div className="input-group input-group-lg">
              <input type="text" className="form-control" aria-label="Large"
                     aria-describedby="inputGroup-sizing-sm" placeholder={"Пребарај лек по име"}
              onChange={handleInputChange}/>
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
                  data.map( drug =>
                  <tr id={drug.urlId}>
                      <td>{drug.brandName}</td>
                      <td>{drug.genericName}</td>
                      <td>{formatPrice(drug.price)} DEN - MKD</td>
                      <td>
                          <button className="btn btn-primary btn-sm" >Преглед
                          </button>
                      </td>
                  </tr>
                      )
              };
              </tbody>
          </table>
      </div>
            </>
    );
}