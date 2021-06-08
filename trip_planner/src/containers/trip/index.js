import axios from 'axios'
import React, { Component } from 'react'
import { Container, Table, Button, Message } from 'semantic-ui-react'
import './index.css'

export default class Trip extends Component {

    state = {
        trip: [],
        flag: false
    }

    componentDidMount() {
        const url = 'api/trips'
        axios.get(url).then(response => {
            this.setState({
                trip: response.data.data
            })
        })
    }

    handleClick = (obj) => {
        return () => {
            const {flag} = this.state
            if (obj === "Rain") {
                this.setState({flag: !flag})
            }
        }
    }

    render() {
        const { trip, flag } = this.state
        return (
            <div>
                <Container style={{ width: 800, marginTop: 120 }}>
                    <Table striped celled textAlign='center'>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell>Destination</Table.HeaderCell>
                                <Table.HeaderCell>Max Temperature</Table.HeaderCell>
                                <Table.HeaderCell>Low Temperature</Table.HeaderCell>
                                <Table.HeaderCell>Weather</Table.HeaderCell>
                                <Table.HeaderCell>Start</Table.HeaderCell>
                                <Table.HeaderCell>End</Table.HeaderCell>
                                <Table.HeaderCell >Surprise</Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>
                        {
                            trip.map(item => {
                                return (
                                    <Table.Body key={item.id}>
                                        <Table.Row >
                                            <Table.Cell>{item.destination}</Table.Cell>
                                            <Table.Cell>{item.max_temp}</Table.Cell>
                                            <Table.Cell>{item.min_temp} </Table.Cell>
                                            <Table.Cell>{item.weather}</Table.Cell>
                                            <Table.Cell>{item.start_time}</Table.Cell>
                                            <Table.Cell>{item.end_time}</Table.Cell>
                                            <Table.Cell width={2} className={item.weather === 'Rain' ? '': 'hidden'}>
                                                <Button size='tiny' onClick={this.handleClick(item.weather)} color='teal' content='Try Me' />
                                            </Table.Cell>
                                        </Table.Row>
                                    </Table.Body>
                                )
                            })
                        }
                    </Table>
                    <Message info className={flag ? 'display' : 'hidden'}>
                        <Message.Header>It will be rain when you arrive, please be prepared</Message.Header>
                    </Message>
                </Container>
            </div>
        )
    }
}
