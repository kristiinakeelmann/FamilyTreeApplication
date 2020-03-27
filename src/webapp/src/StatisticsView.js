import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {
    Button, Container, Form, FormGroup, Input, Label, Alert, Spinner, Card, CardImg, CardText, CardBody, CardLink,
    CardTitle, CardSubtitle, Row, Col, CardGroup
} from 'reactstrap';
import AppNavbar from './AppNavbar';
import FemaleAvatar from './undraw_female_avatar_w3jk.svg';
import MaleAvatar from './undraw_male_avatar_323b.svg';

class StatisticsView extends Component {

    constructor(props) {
        super(props);
        this.state = {
            persons: [],
            youngestAunt: [],
            youngestUncle: [],
        };

        this.handlePersonChange = this.handlePersonChange.bind(this);

    }

    componentDidMount() {
        this.setState({isLoading: true});
        this.setState({selectedPersonId: 1});
        this.getYoungestAunt();
        this.getYoungestUncle();

        fetch('/persons')
            .then(response => response.json())
            .then(data => this.setState({persons: data, isLoading: false}));
    }

    handlePersonChange(event) {
        const selectedPersonId = event.target.value;
        this.setState({selectedPersonId: selectedPersonId});
        this.getFamilyTree(selectedPersonId);
    }

    getYoungestAunt() {

        let method = 'GET';
        let path = '/youngestaunt';

        fetch(path, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(data => this.setState({youngestAunt: data, isLoading: false}));
    }

    getYoungestUncle() {

        let method = 'GET';
        let path = '/youngestuncle';

        fetch(path, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(data => this.setState({youngestUncle: data, isLoading: false}));
    }

    getErrors() {

    }

    render() {
        const persons = this.state.persons;
        const isLoading = this.state.isLoading;
        const youngestAunt = this.state.youngestAunt.firstName;
        const youngestAuntId = this.state.youngestAunt.id;
        const youngestUncle = this.state.youngestUncle.firstName;
        const youngestUncleId = this.state.youngestUncle.id;

        if (isLoading) {
            return <Spinner style={{width: '3rem', height: '3rem'}} type="grow"/>;
        }

        let optionItems = persons
            .map(person => <option key={person.id}
                                   value={person.id}>{person.firstName + ' ' + person.lastName}</option>);

        const title = <h2>{'Answers to the most burning questions'}</h2>;
        const errorMessage = this.getErrors();

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                {errorMessage ? <Alert color="warning"> {errorMessage} </Alert> : null}
                <Form>
                    <FormGroup>
                        <Label for="exampleSelect">Select Person</Label>
                        <Input type="select" name="selectPerson" id="exampleSelect"
                               value={this.state.selectedPersonId ? this.state.selectedPersonId : null}
                               onChange={this.handlePersonChange}>
                            <option value=""></option>
                            {optionItems}
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Search</Button>{' '}
                    </FormGroup>
                </Form>
                <CardGroup>
                    <Card body className="text-center">
                        <CardTitle>Youngest Aunt</CardTitle>
                        <img class="center" src={FemaleAvatar} alt="FemaleAvatar"/>
                        <CardText>{youngestAunt}</CardText>
                        <CardLink href="#" tag={Link} to={"/person/" + youngestAuntId}>Check her details </CardLink>
                    </Card>
                    <Card body className="text-center">
                        <CardTitle>Youngest Uncle</CardTitle>
                        <img class="center" src={MaleAvatar} alt="MaleAvatar"/>
                        <CardText>{youngestUncle}</CardText>
                        <CardLink href="#" tag={Link} to={"/person/" + youngestUncleId}>Check his details </CardLink>
                    </Card>
                </CardGroup>
            </Container>
        </div>
    }
}

export default withRouter(StatisticsView);