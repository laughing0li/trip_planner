import axios from 'axios'
import React, { Component } from 'react'
import { Container, Checkbox, Button, Table, Pagination, Icon, Message } from 'semantic-ui-react'
import './index.css'


export default class Weather extends Component {

    state = {
        city: '',
        weather: [],
        activePage: 1,
        pages: [],
        ids: [],
        isLoading: false
    }


    handlePaginationChange = (e, { activePage }) => {
        const { city } = this.state
        const url = `api/weather/${city}?start=` + (activePage - 1)
        axios.get(url).then(
            response => {
                const data = response.data.data.content
                this.setState({
                    weather: data
                })
                console.log(data)
                console.log(activePage)
            },
            error => {
                console.log('Failed')
            }
        )
    }

    handleInput = () => {
        return () => {
            const city = this.input.value
            this.setState({ city })
            const url = `api/weather/${city}`
            axios.get(url).then(response => {
                this.setState({
                    weather: response.data.data.content,
                    isLoading: true,
                    pages: response.data.data.totalPages

                })
            })
        }
    }

    // only two weather can be selected 
    handleToggle = (id) => {
        return () => {
            const { ids } = this.state
            ids.push(id * 1)
            this.setState({ ids })
        }
    }

    // add trip
    handleTrip = () => {
        return () => {
            const { ids } = this.state
            const url = 'api/trips'
            axios.post(url, ids).then(response => {
                console.log(response.data.code)
            }).catch((error) => {
                console.log(error)
            })
            console.log(ids)
        }
    }

    render() {
        const { weather, ids, pages, activePage, isLoading } = this.state
        // when isLoading is true，then load the table component
        let table = null;
        if (isLoading) {
            table = <div>
                <Container style={{ width: 600, marginTop: 20 }}>
                    <Table striped celled textAlign='center'>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell>Country</Table.HeaderCell>
                                <Table.HeaderCell>City</Table.HeaderCell>
                                <Table.HeaderCell>Temperature</Table.HeaderCell>
                                <Table.HeaderCell>Weather</Table.HeaderCell>
                                <Table.HeaderCell>Time</Table.HeaderCell>
                                <Table.HeaderCell className={ids.length >= 2 ? 'disableElement' : ''}>Choose</Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>
                        {
                            weather.map(item => {
                                return (
                                    <Table.Body key={item.id}>
                                        <Table.Row >
                                            <Table.Cell>{item.country}</Table.Cell>
                                            <Table.Cell>{item.city}</Table.Cell>
                                            <Table.Cell>{item.temp} </Table.Cell>
                                            <Table.Cell>{item.weather}</Table.Cell>
                                            <Table.Cell width={4}>{item.time}</Table.Cell>
                                            <Table.Cell className={ids.length >= 2 ? 'disableElement' : ''}>
                                                <Checkbox toggle onChange={this.handleToggle(item.id)}
                                                />
                                            </Table.Cell>
                                        </Table.Row>
                                    </Table.Body>
                                )
                            })
                        }
                    </Table>
                </Container>
                <Pagination style={{ marginTop: 30 }}
                    defaultActivePage={activePage}
                    ellipsisItem={{ content: <Icon name='ellipsis horizontal' />, icon: true }}
                    firstItem={{ content: <Icon name='angle double left' />, icon: true }}
                    lastItem={{ content: <Icon name='angle double right' />, icon: true }}
                    prevItem={{ content: <Icon name='angle left' />, icon: true }}
                    nextItem={{ content: <Icon name='angle right' />, icon: true }}
                    totalPages={pages}
                    onPageChange={this.handlePaginationChange}
                />
            </div>
        }


        return (
            <Container style={{ marginTop: 100, width: 600 }}>

                <Message size='small'>
                    <Message.Header>How to use the trip planner</Message.Header>
                    <Message.List>
                        <Message.Item>First Search Which city your destination is</Message.Item>
                        <Message.Item>Then choose the time you will arrive</Message.Item>
                        <Message.Item>Please Only Choose Two infos, after that click submit button</Message.Item>
                    </Message.List>
                </Message>
                <input type="text" className='input' ref={(c) => { this.input = c }} placeholder='city name...' />
                <Button onClick={this.handleInput()} style={{ marginLeft: 10 }} content='Search' />
                <Button onClick={this.handleTrip()} style={{ marginLeft: 10 }} content='Submit' />
                {table}

            </Container>
        )
    }
}
