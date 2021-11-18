FROM cypress/browsers:node14.17.0-chrome91-ff89

USER node
WORKDIR /home/node

COPY --chown=node:node . /home/node

RUN npm ci

ENTRYPOINT ["npm", "run"]
CMD ["test:record"]