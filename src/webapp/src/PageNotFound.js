import React, {Component} from 'react';
import {Container, Card, CardText, CardGroup, CardLink} from 'reactstrap';
import AppNavbar from './AppNavbar';
import PageNotFoundImage from './illustrations/undraw_page_not_found_su7k.svg';
import {Link} from "react-router-dom";

export default class PageNotFound extends Component {

    render() {
        const title = <h2>{'Page Not Found'}</h2>;
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <CardGroup>
                    <Card body className="text-center">
                        <img class="center" src={PageNotFoundImage} alt="AncestorAvatar"/>
                        <CardText>Genealogy isn't always easy!</CardText>
                        <CardLink href="#" tag={Link} to={"/persons"}>Return back to your family</CardLink>
                    </Card>
                </CardGroup>
            </Container>
        </div>
    }
}


