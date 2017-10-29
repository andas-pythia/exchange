job("pythia-exchange-build") {
    description "Pythia Exchange Build Job"

    steps {
        shell 'echo Build, build, build.'
    }
}

job("pythia-exchange-deploy") {
    description "Pythia Exchange Deploy Job"

    steps {
        shell 'echo We deploying now.'
    }
}


job("pythia-exchange-pr") {
    description "Pythia Exchange Pull Request Job"

    steps {
        shell 'echo Pull that request.'
    }

