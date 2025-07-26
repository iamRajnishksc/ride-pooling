Summary
1.1 Issue
We need to reliably verify the integrity and identity of Android devices accessing secure backend services. Devices must prove they are unrooted, running trusted firmware, and are accessing via our official app. The solution should be resistant to tampering and emulator spoofing.

1.2 Decision
We will use Android’s hardware-backed Keystore with certificate attestation, validated on the server using a Google-signed certificate chain and a per-session challenge.

1.3 Status
✅ Accepted

2. Details
2.1 Assumptions
Android OS supports Key Attestation API (API 24+)

Device hardware has TEE or StrongBox support

The backend can maintain pinned Google root certificates

Our app can call the AndroidKeyStore and send the attestation data to the server

2.2 Constraints
Requires devices with proper hardware support (some older/emulators won't work)

Validation logic must run offline (no OCSP/CRL lookups)

Only devices with proper Google CA chain can be trusted

2.3 Positions
✅ For: Strong cryptographic assurance of device integrity

❌ Against: Using Play Integrity API due to server-side limitations or dependency on Play Services

✅ For: Attestation extension check using embedded challenge and package name

✅ For: Rejecting chains not rooted in Google CA (e.g., self-signed)

2.4 Argument
Hardware-backed attestation provides the highest level of trust available on Android without requiring user action. The presence of a signed certificate chain from Google Attestation CA, along with a verified attestation extension, ensures that only authentic, unmodified devices can participate.

2.5 Implications
Implementation must include:

Client-side: Key generation + attestation challenge + signature

Server-side: Cert chain validation + extension decoding + challenge verification

Client must not cache or reuse certificates

Each request should use a fresh challenge to prevent replay

Devices without TEE will be blocked or handled as untrusted

3. Related
3.1 Related decisions
ADR-12: Skip Play Integrity API for cert-based attestation

ADR-08: Enforce package name and certificate digest verification

3.2 Related requirements
REQ-009: Device Trust Enforcement

REQ-015: Reject rooted/emulated clients

3.3 Related artifacts
AttestationServer.java

MainActivity.kt (keypair generation)

cert-validation.module (Java)

3.4 Related principles
Zero Trust by default

Hardware-backed security preference

Offline certificate validation

4. Notes
4.1 TBD
OCSP/CRL optional integration

StrongBox fallback strategy

Dynamic root cert updates (if required)

Would you like the same ADR version in Markdown, Confluence Wiki markup, or as a downloadable .adoc file? I can also generate versions for client-side or server-side attestation flow separately if needed.







