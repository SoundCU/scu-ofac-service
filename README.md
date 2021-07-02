# Sound OFAC Service

A new and improved OFAC search running as Spring service.

### Source Data
 * for the sake of performance, all of the source data resides in memory instead of a database.

 * all lists are pulled from a [single source](http://api.trade.gov/static/consolidated_screening_list/consolidated.json) maintained and published by the [Federal Trade Administration](https://www.trade.gov/)

 * The source data is checked for updates and reloaded hourly

### Show Me What You Got!

[Sound OFAC Service Swagger](https://app.swaggerhub.com/apis/SoundCU/Sound_OFAC_Service)

### Where Have All My Logs Gone?

All logs are directed to stdout/stderr

