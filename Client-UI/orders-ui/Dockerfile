FROM node:8-alpine as build-front-stage

RUN npm install -g http-server

RUN node -v
RUN npm -v

WORKDIR /app
RUN apk update && apk upgrade && \
    apk add --no-cache bash git openssh

COPY ./ ./

RUN npm install && npm cache clean --force && npm run build && ls -l /app/target/dist/static/

FROM php:5.6-apache

COPY ./DockerFiles/front.conf /etc/apache2/sites-enabled/
RUN mkdir /var/www/frontORDERS

COPY --from=build-front-stage /app/target/dist/ /var/www/frontORDERS/
RUN chown -R www-data.www-data /var/www/frontORDERS

RUN a2enmod rewrite && a2enmod proxy && a2enmod proxy_http && a2enmod proxy_connect