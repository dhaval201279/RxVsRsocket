# Source code of my [blog](https://www.dhaval-shah.com/performance-comparison-rsocket-webflux/) on [RSocket](https://principlesofchaos.org/) and comparing its performance against _Webflux_  

#### Card Client APIs
Edge application responsible for consuming [Card Service APIs](https://github.com/dhaval201279/RxVsRSocketServer)

| HTTP Method   | URI     | Description   |
| :--------:  | :--------: | :------ |
| GET | rx/card/{id} | Fetches a card based on cardId by using _WebClient_ |
| GET | rsocket/card/{id} | Fetches a card based on cardId by using _RSocket_ |