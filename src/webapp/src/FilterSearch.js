import React from 'react';
import ButtonGroup from "reactstrap/es/ButtonGroup";
import { Button } from "reactstrap";
import { Link } from "react-router-dom";

export default class FilterSearch extends React.Component {

    state = {
        initialItems: [],
        items: []
    }


    filterList = (event) => {
        let items = this.state.initialItems;
        let key = event.target.value;

        if (key != '') {
            items = items.filter(function (item) {
                if (item.firstName.toLowerCase().includes(key.toLowerCase())) {
                    return true;
                }
                if (item.lastName.toLowerCase().includes(key.toLowerCase())) {
                    return true;
                }
                if (item.sex.toLowerCase().includes(key.toLowerCase())) {
                    return true;
                }
                return false;
            });
        }
        this.setState({items: items});
    }


    componentWillMount = () => {
        this.setState({
            initialItems: this.props.content,
            items: this.props.content
        })
    }


    render() {
        const personReactComponents = this.state.items.map(person => {
            return <tr key={person.id}>
                <td>{person.firstName}</td>
                <td>{person.lastName}</td>
                <td>{person.sex}</td>
                <td>{person.dateOfBirth}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/person/" + person.id}>Edit</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
        return (
            <div>
                <form>
                    <input type="text" placeholder="Search" onChange={this.filterList}/>
                </form>
                {personReactComponents}
            </div>
        );
    }
};