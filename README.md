# Voting Application

This is a simple voting application that allows users to vote for their favorite candidate.
The application is written in Java with Spring and is meant as an exercise for me.

**Objectives:**
* Learn how to use Spring framework
* Learn how to integrate DB with Spring
* Learn how to integrate Message Queue with Spring

**Requirements:**
* For now, we will already start with a set of candidates ready for voting
* Users must be able to vote for their favorite candidate on `/api/vote/{candidateId}` endpoint
* Users must be able to see the current vote count for each candidate on `/api/vote` endpoint

**Non-functional requirements:**
* There will be high traffic on the voting endpoint
* There will be high traffic on the vote count endpoint