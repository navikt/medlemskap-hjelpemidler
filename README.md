# medlemskap-hjelpemidler
Medlemskapsvurderinger for DigiHOT


## URL til tjeneste
* preprod: https://medlemskap-hjelpemidler.intern.dev.nav.no/vurdering  -- POST
* prod: https://medlemskap-hjelpemidler.intern.nav.no/vurdering  -- POST

## Autentisering
Forventer et AzureAD-token utstedt til servicebruker, satt Authorization-header (Bearer)

## Azure AD Scope
| Azure scope                                     | Miljø    |
|-------------------------------------------------|----------|
| api://dev-gcp.medlemskap-hjelpemidler/.default  | GCP-DEV  |
| api://prod-gcp.medlemskap-hjelpemidler/.default | GCP-PROD |

## Headere
I tillegg til Authorization-headeren kreves det at Content-Type er satt til application/json


## Eksempel request 
```
{
    "fnr":"12345678901",
}
```
## Eksempel respons 
```
{
    "status": "JA"
}

```
# Avhengigheter
* Medlemskap-Oppslag (neste versjon)


