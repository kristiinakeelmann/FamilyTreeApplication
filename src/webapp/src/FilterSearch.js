import React from 'react';
import ButtonGroup from "reactstrap/es/ButtonGroup";
import { Button, Container, FormGroup, Label, Table, Input } from "reactstrap";
import {Link} from "react-router-dom";

export default class FilterSearch extends React.Component {

    state = {
        initialItems: [],
        items: []
    }


    filterList = (event) => {
        let items = this.state.initialItems;
        let key = event.target.value;

        if (key !== '') {
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


    componentDidMount = () => {
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
                <Container>
                    <h2>Persons</h2>
                    <FormGroup>
                        <Label>Search for person</Label>
                        <Input type="text" placeholder="Search" onChange={this.filterList}/>
                    </FormGroup>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">First name</th>
                            <th width="20%">Last name</th>
                            <th width="20%">Sex</th>
                            <th width="20%">Birthday</th>
                            <th width="20%"></th>
                        </tr>
                        </thead>
                        <tbody>
                        {personReactComponents}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
};