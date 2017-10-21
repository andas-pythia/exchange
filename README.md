[![Build Status](https://travis-ci.org/andas-pythia/exchange.svg?branch=master)](https://travis-ci.org/andas-pythia/exchange)

# Exchange Request Engine

##### Emits
- TaskID for tracking request

##### Depends
- Processor modules
    - These are the modules which will register with the request engine and pull a task they are typed for. The modules will put the payload back into the processing engine / queue.

- Datastore Holds
    - Sources
        - NASDAQ
        - NYSE
        - OTCMKTS
        - TPE
        - HKG
        - KRX
    - Exchange Symbols
    - Historical Exchange Data


------

### Possible schema

##### EXCHANGE
Table describes the Exchanges and their capabilities.
- `id` - `INT, unique`
- `name` - `VARCHAR`
- `description` - `VARCHAR`
- `capabilites` - `VARCHAR, DICT, FOREIGN_KEY`
    - *TODO*: What kind of capabilities are we talking about here?
- `currency` - `INT, FOREIGN_KEY`
    - Integer type mapped to `CURRENCY`

##### EXCHANGE_CAPABILITY
Table describes the capabilites / limitations of an exchange: options, futures, commodities, stocks, bonds, etfs, mutual funds.
- `id` - `INT, unique`
- `name` - `VARCHAR`
- `description` - `VARCHAR`

##### CURRENCY
Table describes the currencies available for the various exchanges and the exchange rates associated with them. This should be updated daily. 
- `id` - `INT`
- `name` - `VARCHAR`
- `description` - `VARCHAR`
- `country` - `INT`
    - Integer types mapped to `COUNTRY`
- `value` - `FLOAT`
    - Current value of the `currency`
- `last_updated` - `datetime`

#### SOURCE
Defines the actual sources for data for the Exchange component.
- `id` - `INT, unique`
- `name` - `VARCHAR`
    - Name of the source
- `description` - `VARCHAR, NULLABLE`
    - Describes the source
- `type` - `INT`
    - Integer type mapped to a `SOURCE_TYPE`
- `exchange` - `INT, FOREIGN_KEY`
    - Integer type mapped to `EXCHANGES`

##### SOURCE_TYPE
Cannot be deleted while there is a reference to the table from the `SOURCES` table.
- `id` - `INT, unique`
- `name` - `VARCHAR`
    - This describes the type of source in the Exchange component. Considering the following as valid: "historical", "real-time", "on-demand"
- `description` - `VARCHAR`
    - Describes the type of source

##### VEHICLE
Table used to house exchange data - https://stackoverflow.com/a/9953018
- `id` - `INT, unique`
- `symbol` - `VARCHAR`
- `timestamp` - `datetime`
- `close` - `FLOAT`
- `high` - `FLOAT`
- `low` - `FLOAT`
- `open` - `FLOAT`
- `volume` - `INT`
- `exchange_id` - `INT, FOREIGN_KEY`
- `source_id` - `INT, FOREIGN_KEY`

##### INDEX_FUNDS
Andas-defined exchange index funds This is user-changeable, but will be primarily directed by Pythia
- `id` - `INT, unique`
- `exchange` - `INT`
- `ticker` - `INT`
- `sector_list` - `LIST, FOREIGN_KEY`
        This is a `LIST`; usually contains one `SECTOR`, but may contain hybrids

##### SECTOR
This is going to be how andas-led index funds are going to be categorized. Exmaples would be: "green energy, internet advertising, advertising, etc."
`id` - `INT, unique`
`name` - `VARCHAR`
`description` - `VARCHAR`

    

##### unfiltered info
Historical data will need to be retrieved for local sources first if it is available. If not, it will be retrieved from the relevant source (if one is available in the SOURCES table).
